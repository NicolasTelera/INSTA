package com.octo.demo.repository;

import com.octo.demo.exception.StarWarsException;
import com.octo.demo.exception.StarWarsNotFoundException;
import com.octo.demo.model.Character;

public interface MyRepository {
    Character findCharacterById(int id)
            throws StarWarsException, StarWarsNotFoundException;

}
