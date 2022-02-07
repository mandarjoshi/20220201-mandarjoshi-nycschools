package com.mandarjoshi.nycschools.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mandarjoshi.nycschools.repo.SchoolRepository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final SchoolRepository schoolRepository;

    @Inject
    public ViewModelFactory(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new MainViewModel(this.schoolRepository);
    }
}
