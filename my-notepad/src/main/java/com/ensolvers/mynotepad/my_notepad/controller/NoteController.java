package com.ensolvers.mynotepad.my_notepad.controller;

import com.ensolvers.mynotepad.my_notepad.controller.api.NoteApi;
import com.ensolvers.mynotepad.my_notepad.controller.mapper.NoteRestMapper;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteFilterRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListUpdateCategoryRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListUpdateStateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.NoteResponse;
import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.note}")
public class NoteController implements NoteApi {

    private final NoteService service;

    private final NoteRestMapper restMapper;

    @PostMapping
    @Override
    public ResponseEntity<NoteResponse> save(NoteRequest request) throws ApiException {
        var result = service.save(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<NoteResponse> update(NoteUpdateRequest request) throws ApiException {
        var result = service.update(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.OK);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Void> delete(NoteListRequest request) {
        service.deleteNotesByIds(request.getIds(), request.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/state")
    @Override
    public ResponseEntity<List<NoteResponse>> updateState(NoteListUpdateStateRequest request) {
        var result = service.updateStates(request.getIds(), request.getUserId(), NoteState.valueOf(request.getState()));
        return new ResponseEntity<>(restMapper.dtoToResponses(result), HttpStatus.OK);
    }

    @PatchMapping("/category")
    @Override
    public ResponseEntity<List<NoteResponse>> updateCategory(NoteListUpdateCategoryRequest request) throws ApiException {
        var result = service.updateCategory(request.getIds(), request.getUserId(), request.getCategoryId());
        return new ResponseEntity<>(restMapper.dtoToResponses(result), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<NoteResponse>> getAllByUser(NoteFilterRequest request) {
        var result = service.findAllByUser(request.getUserId(), request.getCategoryId());
        return new ResponseEntity<>(restMapper.dtoToResponses(result), HttpStatus.OK);
    }
}
