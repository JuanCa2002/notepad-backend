package com.ensolvers.mynotepad.my_notepad.controller.mapper;

import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.CategoryResponse;
import com.ensolvers.mynotepad.my_notepad.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRestMapper {

    @Mapping(target = "user.id", source = "userId")
    CategoryDto requestToDto(CategoryRequest request);

    CategoryDto requestToDto(CategoryUpdateRequest request);

    @Mapping(target = "userId", source = "user.id")
    CategoryResponse dtoToResponse(CategoryDto dto);

    List<CategoryResponse> dtoToResponses(List<CategoryDto> dtoList);
}
