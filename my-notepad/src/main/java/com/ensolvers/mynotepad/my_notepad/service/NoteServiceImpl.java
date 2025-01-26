package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.CategoryDto;
import com.ensolvers.mynotepad.my_notepad.dto.NoteDto;
import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import com.ensolvers.mynotepad.my_notepad.entity.CategoryEntity;
import com.ensolvers.mynotepad.my_notepad.entity.NoteEntity;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.exception.note.NoteNotFoundException;
import com.ensolvers.mynotepad.my_notepad.mapper.NoteMapper;
import com.ensolvers.mynotepad.my_notepad.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository repository;

    private final NoteMapper mapper;

    private final UserService userService;

    private final CategoryService categoryService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public NoteDto save(NoteDto noteDto) throws ApiException{
        // Validate if the given user exists
        userService.findById(noteDto.getUser().getId());

        // Validate if the given category exists if it is not null
        CategoryDto categoryDto = null;
        if(noteDto.getCategory() != null && noteDto.getCategory().getId()!= null){
            categoryDto = categoryService.findByIdAndUser(noteDto.getCategory().getId(), noteDto.getUser().getId());
        }

        // Map noteDto to noteEntity
        noteDto.setCategory(categoryDto);
        NoteEntity entity = mapper.dtoToEntity(noteDto);

        // Set creation date, creation time and state to the new note
        entity.setCreationDate(LocalDate.now());
        entity.setCreationTime(LocalTime.now());
        entity.setState(NoteState.ACTIVE);

        // Save note in the database
        return  mapper.entityToDto(repository.save(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public NoteDto update(NoteDto noteDto) throws ApiException {
        // Find note to update
        NoteDto foundNote = findById(noteDto.getId());

        // Handle category logic
        if (noteDto.getCategory() == null || noteDto.getCategory().getId() == null) {
            // Keep the previous category if the category is not provided
            noteDto.setCategory(foundNote.getCategory());
        } else if (noteDto.getCategory().getId() == 0) {
            // Set the category to null if the ID is zero
            foundNote.setCategory(null);
            noteDto.setCategory(null);
        } else {
            // Validate and set the new category if provided
            categoryService.findByIdAndUser(noteDto.getCategory().getId(), foundNote.getUser().getId());
        }

        // Merge previous note with the current one
        mapper.mergeToUpdate(foundNote, noteDto);

        // Map dto to entity
        NoteEntity entity = mapper.dtoToEntity(foundNote);

        // Update note
        return mapper.entityToDto(repository.save(entity));
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public NoteDto findById(Long id) throws ApiException {
        // Validate if the note exists by the given ID
        NoteNotFoundException errorNotFound = new NoteNotFoundException();
        errorNotFound.addParams(id);
        NoteEntity foundNote = repository.findById(id).orElseThrow(() -> errorNotFound);

        // Map entity to dto
        return mapper.entityToDto(foundNote);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public void deleteNotesByIds(List<Long> ids, BigInteger userId) {
        // Get notes by ids
        List<NoteEntity> notes = repository.findAllById(ids);

        // Filter notes by user id
        List<NoteEntity> foundNotes = notes.stream().filter(n -> Objects.equals(n.getUser().getId(), userId)).toList();

        // Delete all by found notes by ids and user id
        repository.deleteAll(foundNotes);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public List<NoteDto> updateStates(List<Long> ids, BigInteger userId, NoteState state) {
        // Get notes by ids
        List<NoteEntity> notes = repository.findAllById(ids);

        // Filter notes by user id
        List<NoteEntity> foundNotes = notes.stream().filter(n -> Objects.equals(n.getUser().getId(), userId)).toList();

        // Set state to the notes
        foundNotes.forEach(n -> n.setState(state));

        // Update all the notes
        return mapper.entitiesToDto(repository.saveAll(foundNotes));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public List<NoteDto> updateCategory(List<Long> ids, BigInteger userId, Integer categoryId) throws ApiException{
        // Validate if the given category exist for this user
        if(categoryId!=0){
            categoryService.findByIdAndUser(categoryId, userId);
        }

        // Get notes by ids
        List<NoteEntity> notes = repository.findAllById(ids);

        // Filter notes by user id
        List<NoteEntity> foundNotes = notes.stream().filter(n -> Objects.equals(n.getUser().getId(), userId)).toList();

        // Set category to the notes
        CategoryEntity category;
        if(categoryId != 0){
            category = new CategoryEntity();
            category.setId(categoryId);
        } else {
            category = null;
        }
        foundNotes.forEach(n -> n.setCategory(category));

        // Update all the notes
        return mapper.entitiesToDto(repository.saveAll(foundNotes));
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public List<NoteDto> findAllByUser(BigInteger userId, Integer categoryId) {
        // Get all notes by user id
        return mapper.entitiesToDto(repository.findAllByUser(userId, categoryId));
    }
}
