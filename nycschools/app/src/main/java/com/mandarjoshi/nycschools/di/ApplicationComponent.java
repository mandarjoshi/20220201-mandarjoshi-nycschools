package com.mandarjoshi.nycschools.di;

import com.mandarjoshi.nycschools.ui.SchoolScoresFragment;
import com.mandarjoshi.nycschools.ui.main.SchoolFragment;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface ApplicationComponent{
    void inject(SchoolFragment schoolFragment);
    void inject(SchoolScoresFragment schoolScoresFragment);
    ViewModelFactory viewModelFactory();
}
