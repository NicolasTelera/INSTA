package com.octo.demo.interactor;

import com.octo.demo.exception.StarWarsException;
import com.octo.demo.exception.StarWarsNotFoundException;
import com.octo.demo.model.Character;
import com.octo.demo.repository.MyRepository;

public class MyInteractor {

    private MyRepository repository;

    public MyInteractor(MyRepository repository) {
        this.repository = repository;
    }

    public Character getCharacterById(int id)
        throws StarWarsException, StarWarsNotFoundException {
        return repository.findCharacterById(id);
    }

}
