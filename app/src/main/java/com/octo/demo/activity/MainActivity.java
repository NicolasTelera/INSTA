package com.octo.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.demo.DaggerMainComponent;
import com.octo.demo.dagger.MyModule;
import com.octo.demo.presenter.MyPresenter;
import com.octo.demo.R;
import com.octo.demo.viewmodel.CharacterViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyView {

    @Inject MyPresenter presenter;

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.characterNameTextView) TextView characterNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerMainComponent.builder()
                           .build()
                           .plus(new MyModule(this))
                           .inject(this);
        presenter.load(1);
    }

    @Override
    public void displayCharacterName(CharacterViewModel viewModel) {
        hideProgressBar();
        characterNameTextView.setText(viewModel.getName());
        characterNameTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayNotFound() {
        Toast.makeText(this, "Character not found!", Toast.LENGTH_SHORT).show();
        hideProgressBar();
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        hideProgressBar();
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
