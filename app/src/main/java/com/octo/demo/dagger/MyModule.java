package com.octo.demo.dagger;

import com.octo.demo.interactor.MyInteractor;
import com.octo.demo.presenter.MyPresenter;
import com.octo.demo.presenter.MyPresenterImpl;
import com.octo.demo.repository.MyRepository;
import com.octo.demo.activity.MyView;
import com.octo.demo.repository.MyRepositoryImpl;
import com.octo.demo.viewmodel.CharacterViewModel;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    private final MyView view;

    public MyModule(MyView view) {
        this.view = view;
    }

    @Provides
    MyView providesMyView(@Named(MainModule.FRONT) Executor executor) {
        return new MyViewDecorator(executor, view);
    }

    @Provides
    MyPresenter providesMyPresenter(
        @Named(MainModule.BACKGROUND) Executor executor,
        MyInteractor interactor,
        MyView view
    ) {
        return new MyPresenterDecorator(executor, new MyPresenterImpl(interactor, view));
    }

    @Provides
    MyInteractor providesMyinteractor(MyRepository repository) {
        return new MyInteractor(repository);
    }

    @Provides
    MyRepository providesMyRepository() {
        return new MyRepositoryImpl();
    }

    class MyViewDecorator implements MyView {

        private Executor executor;
        private MyView view;

        MyViewDecorator(Executor executor, MyView view) {
            this.executor = executor;
            this.view = view;
        }

        @Override
        public void displayCharacterName(final CharacterViewModel viewModel) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayCharacterName(viewModel);
                }
            });
        }

        @Override
        public void displayNotFound() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayNotFound();
                }
            });
        }

        @Override
        public void displayError() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayError();
                }
            });

        }
    }

    class MyPresenterDecorator implements MyPresenter {

        private Executor executor;
        private MyPresenter presenter;

        MyPresenterDecorator(Executor executor, MyPresenter presenter) {
            this.executor = executor;
            this.presenter = presenter;
        }

        @Override
        public void load(final int id) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    presenter.load(id);
                }
            });
        }
    }
}
