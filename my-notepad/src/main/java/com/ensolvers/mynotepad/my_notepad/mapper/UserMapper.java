package com.ensolvers.mynotepad.my_notepad.mapper;

import com.ensolvers.mynotepad.my_notepad.dto.UserDto;
import com.ensolvers.mynotepad.my_notepad.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto entityToDto(UserEntity entity);

    UserEntity dtoToEntity(UserDto dto);

    void mergeToUpdate(@MappingTarget UserDto target, UserDto source);
}
