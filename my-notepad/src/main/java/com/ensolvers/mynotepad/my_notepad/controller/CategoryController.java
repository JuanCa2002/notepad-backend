package com.ensolvers.mynotepad.my_notepad.controller;

import com.ensolvers.mynotepad.my_notepad.controller.api.CategoryApi;
import com.ensolvers.mynotepad.my_notepad.controller.mapper.CategoryRestMapper;
import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserIdRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.CategoryResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.category}")
public class CategoryController implements CategoryApi {

    private final CategoryService service;

    private final CategoryRestMapper restMapper;

    @PostMapping
    @Override
    public ResponseEntity<CategoryResponse> save(CategoryRequest request) throws ApiException {
        var result = service.save(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<CategoryResponse> update(CategoryUpdateRequest request) throws ApiException {
        var result = service.update(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer id, UserIdRequest request) throws ApiException {
        service.removeById(id, request.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @Override
    public ResponseEntity<List<CategoryResponse>> getAllByUser(@PathVariable BigInteger userId) {
        return new ResponseEntity<>(restMapper.dtoToResponses(service.findAllByUser(userId)), HttpStatus.OK);
    }
}
