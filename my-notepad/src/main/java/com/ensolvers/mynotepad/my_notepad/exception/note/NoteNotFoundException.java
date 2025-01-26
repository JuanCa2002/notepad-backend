package com.ensolvers.mynotepad.my_notepad.exception.note;

import com.ensolvers.mynotepad.my_notepad.exception.general.NotFoundException;

public class NoteNotFoundException extends NotFoundException {

    public NoteNotFoundException() {
        super(NoteApiErrorMessages.NOTE_NOT_FOUND);
    }
}
