package com.octo.demo.dagger;

import com.octo.demo.activity.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MyModule.class)
public interface MyComponent {
    void inject (MainActivity activity);
}
