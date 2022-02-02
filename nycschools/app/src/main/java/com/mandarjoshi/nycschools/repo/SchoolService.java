package com.mandarjoshi.nycschools.repo;


import com.mandarjoshi.nycschools.model.SchoolDetails;
import com.mandarjoshi.nycschools.model.SchoolScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolService {

    @GET("/resource/f9bf-2cp4.json")
    Call<List<SchoolScore>> getSchoolScores();

    @GET("/resource/s3k6-pzi2.json")
    Call<List<SchoolDetails>> getSchoolList();

}
