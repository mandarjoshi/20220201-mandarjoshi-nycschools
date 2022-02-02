package com.mandarjoshi.nycschools.di;

import com.mandarjoshi.nycschools.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface ApplicationComponent{
    void inject(MainViewModel mainViewModel);
}
