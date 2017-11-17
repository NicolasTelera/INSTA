package com.octo.demo.presenter;

import com.octo.demo.interactor.MyInteractor;
import com.octo.demo.exception.StarWarsException;
import com.octo.demo.exception.StarWarsNotFoundException;
import com.octo.demo.activity.MyView;
import com.octo.demo.model.Character;
import com.octo.demo.viewmodel.CharacterViewModel;

public class MyPresenterImpl implements MyPresenter {

    private final MyInteractor interactor;
    private final MyView view;

    public MyPresenterImpl(MyInteractor interactor, MyView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void load(int id) {
        try {
            Character character = interactor.getCharacterById(id);
            final CharacterViewModel viewModel = new CharacterViewModel(character.getName());
            view.displayCharacterName(viewModel);
        } catch (StarWarsException e) {
            view.displayError();
        } catch (StarWarsNotFoundException e) {
            view.displayNotFound();
        }
    }
}
