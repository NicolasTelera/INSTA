package com.octo.demo.activity;

import com.octo.demo.viewmodel.CharacterViewModel;

public interface MyView {
    void displayCharacterName(CharacterViewModel viewModel);
    void displayNotFound();
    void displayError();
}
