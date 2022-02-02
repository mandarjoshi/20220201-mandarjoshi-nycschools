package com.mandarjoshi.nycschools;

import android.app.Application;

import com.mandarjoshi.nycschools.di.ApplicationComponent;
import com.mandarjoshi.nycschools.di.DaggerApplicationComponent;

public class NycSchoolApplication extends Application {

    // Reference to the application graph that is used across the whole app
    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

}