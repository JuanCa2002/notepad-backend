package com.ensolvers.mynotepad.my_notepad.controller.mapper;

import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.UserResponse;
import com.ensolvers.mynotepad.my_notepad.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    UserDto requestToDto(UserRequest request);

    UserDto requestToDto(UserUpdateRequest request);

    UserResponse dtoToResponse(UserDto dto);
}
