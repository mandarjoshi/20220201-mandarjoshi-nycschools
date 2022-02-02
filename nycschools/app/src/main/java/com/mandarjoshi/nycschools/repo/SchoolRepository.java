package com.mandarjoshi.nycschools.repo;

import com.mandarjoshi.nycschools.model.SchoolDetails;
import com.mandarjoshi.nycschools.model.SchoolScore;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class SchoolRepository {

    private final SchoolService schoolService;

    @Inject
    public SchoolRepository(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    public Call<List<SchoolScore>> getSchoolScores(){
        return schoolService.getSchoolScores();
    }

    public Call<List<SchoolDetails>> getSchoolList(){
        return schoolService.getSchoolList();
    }

}
