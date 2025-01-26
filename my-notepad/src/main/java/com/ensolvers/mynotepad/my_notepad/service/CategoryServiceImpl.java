package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.CategoryDto;
import com.ensolvers.mynotepad.my_notepad.entity.CategoryEntity;
import com.ensolvers.mynotepad.my_notepad.exception.category.CategoryApiErrorMessages;
import com.ensolvers.mynotepad.my_notepad.exception.category.CategoryBusinessException;
import com.ensolvers.mynotepad.my_notepad.exception.category.CategoryNotFoundException;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.mapper.CategoryMapper;
import com.ensolvers.mynotepad.my_notepad.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    private final UserService userService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public CategoryDto save(CategoryDto categoryDto) throws ApiException{
        // Validate if the given user exists otherwise throw an exception
        userService.findById(categoryDto.getUser().getId());

        // Map categoryDTO to categoryEntity
        CategoryEntity entity = mapper.dtoToEntity(categoryDto);

        // Validate if a category with the given NAME exists in the database
        CategoryBusinessException error = new CategoryBusinessException(CategoryApiErrorMessages.CATEGORY_NAME_ALREADY_EXISTS);
        if(repository.existsByName(categoryDto.getName(), categoryDto.getUser().getId())) throw error;

        // Save category in the database
        return mapper.entityToDto(repository.save(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public CategoryDto update(CategoryDto categoryDto) throws ApiException {
        // Validate if a category with the given ID exists in the database
        CategoryNotFoundException errorNotFound = new CategoryNotFoundException();
        errorNotFound.addParams(categoryDto.getId());
        CategoryEntity foundEntity = repository.findById(categoryDto.getId())
                .orElseThrow(() -> errorNotFound);

        // Validate if a category with the given NAME exists in the database
        CategoryBusinessException error = new CategoryBusinessException(CategoryApiErrorMessages.CATEGORY_NAME_ALREADY_EXISTS);
        if(repository.existsByName(categoryDto.getName(), foundEntity.getUser().getId())) throw error;

        // Update the category's name with the value from the DTO
        foundEntity.setName(categoryDto.getName());

        // Save the updated category entity and map it to a DTO for the response
        return mapper.entityToDto(repository.save(foundEntity));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public void removeById(Integer id, BigInteger userId) throws ApiException {
        // Validate if a category with the given ID exists in the database
        CategoryNotFoundException errorNotFound = new CategoryNotFoundException();
        errorNotFound.addParams(id);
        CategoryEntity foundEntity = repository.findByIdAndUser(id, userId).orElseThrow(() -> errorNotFound);

        // Validate if the given category has a relation with a note
        if(existsByCategory(id)) throw new CategoryBusinessException(CategoryApiErrorMessages.CATEGORY_ALREADY_ASSIGNED);

        // Delete the found category from the database
        repository.delete(foundEntity);
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public List<CategoryDto> findAllByUser(BigInteger userId) {
        // Get all categories from database
        return mapper.entitiesToDto(repository.findAllByUser(userId));
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public CategoryDto findByIdAndUser(Integer id, BigInteger userId) throws ApiException {
        // Validate if a category exists by the given ID
        CategoryNotFoundException errorNotFound = new CategoryNotFoundException();
        errorNotFound.addParams(id);
        CategoryEntity foundCategory = repository.findByIdAndUser(id, userId).orElseThrow(() -> errorNotFound);

        // Return the found category and map it into Dto
        return mapper.entityToDto(foundCategory);
    }

    @Override
    public boolean existsByCategory(Integer categoryId) {
        // If a category is already assigned to a note return TRUE otherwise FALSE
        return repository.existsByCategory(categoryId);
    }
}
