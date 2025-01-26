package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.NoteDto;
import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;

import java.math.BigInteger;
import java.util.List;

public interface NoteService {

    NoteDto save(NoteDto noteDto) throws ApiException;

    NoteDto update(NoteDto noteDto) throws ApiException;

    NoteDto findById(Long id) throws ApiException;

    void deleteNotesByIds(List<Long> ids, BigInteger userId);

    List<NoteDto> updateStates(List<Long> ids, BigInteger userId, NoteState state);

    List<NoteDto> updateCategory(List<Long> ids, BigInteger userId, Integer categoryId) throws ApiException;

    List<NoteDto> findAllByUser(BigInteger userId, Integer categoryId);
}
