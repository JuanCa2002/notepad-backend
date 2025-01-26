package com.ensolvers.mynotepad.my_notepad.mapper;

import com.ensolvers.mynotepad.my_notepad.dto.NoteDto;
import com.ensolvers.mynotepad.my_notepad.entity.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoteMapper {

    NoteDto entityToDto(NoteEntity entity);

    NoteEntity dtoToEntity(NoteDto dto);

    List<NoteDto> entitiesToDto(List<NoteEntity> entities);

    void mergeToUpdate(@MappingTarget NoteDto target, NoteDto source);
}
