package com.ensolvers.mynotepad.my_notepad.controller.mapper;

import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.NoteResponse;
import com.ensolvers.mynotepad.my_notepad.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoteRestMapper {

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "user.id", source = "userId")
    NoteDto requestToDto(NoteRequest request);

    @Mapping(target = "category.id", source = "categoryId")
    NoteDto requestToDto(NoteUpdateRequest request);

    @Mapping(target = "userId", source = "user.id")
    NoteResponse dtoToResponse(NoteDto dto);

    List<NoteResponse> dtoToResponses(List<NoteDto> dtoList);
}
