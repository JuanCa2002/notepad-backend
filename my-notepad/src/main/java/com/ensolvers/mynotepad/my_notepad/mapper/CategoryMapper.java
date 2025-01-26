package com.ensolvers.mynotepad.my_notepad.mapper;

import com.ensolvers.mynotepad.my_notepad.dto.CategoryDto;
import com.ensolvers.mynotepad.my_notepad.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryEntity dtoToEntity(CategoryDto dto);

    CategoryDto entityToDto(CategoryEntity entity);

    List<CategoryDto> entitiesToDto(List<CategoryEntity> entities);
}
