package com.ensolvers.mynotepad.my_notepad.exception.category;

import com.ensolvers.mynotepad.my_notepad.exception.general.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super(CategoryApiErrorMessages.CATEGORY_NOT_FOUND);
    }
}
