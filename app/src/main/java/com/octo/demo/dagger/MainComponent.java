package com.octo.demo.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = MainModule.class)
@Singleton
public interface MainComponent {
    MyComponent plus(MyModule module);
}
