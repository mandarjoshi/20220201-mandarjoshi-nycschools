package com.mandarjoshi.nycschools.di;

import com.mandarjoshi.nycschools.repo.SchoolRepository;
import com.mandarjoshi.nycschools.repo.SchoolService;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {

    @Provides
    public SchoolService providesSchoolService(
            Retrofit retrofit){
        return retrofit.create(SchoolService.class);
    }

    @Provides
    public SchoolRepository providesSchoolRepository(SchoolService schoolService){
        return new SchoolRepository(schoolService);
    }

    public ViewModelFactory providesViewModelfactory(SchoolRepository schoolRepository){
        return new ViewModelFactory(schoolRepository);
    }
}
