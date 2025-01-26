package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.CategoryDto;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;

import java.math.BigInteger;
import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto) throws ApiException;

    CategoryDto update(CategoryDto categoryDto) throws ApiException;

    void removeById(Integer id, BigInteger userId) throws ApiException;

    List<CategoryDto> findAllByUser(BigInteger userId);

    CategoryDto findByIdAndUser(Integer id, BigInteger userId) throws ApiException;

    boolean existsByCategory(Integer categoryId);
}
