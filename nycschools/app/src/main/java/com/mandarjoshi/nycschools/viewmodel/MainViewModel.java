package com.mandarjoshi.nycschools.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mandarjoshi.nycschools.model.SchoolDetails;
import com.mandarjoshi.nycschools.model.SchoolScore;
import com.mandarjoshi.nycschools.repo.SchoolRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private SchoolRepository schoolRepository;
    private static final int BEST_READING_SCORE = 400;

    @Inject
    public MainViewModel(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    private MutableLiveData<List<SchoolDetails>> schoolList;
    public LiveData<List<SchoolDetails>> getSchoolList() {
        if (schoolList == null) {
            schoolList = new MutableLiveData<>();
            populateSchoolList();
        }
        return schoolList;
    }

    public boolean isReadingBestScore(SchoolScore schoolScore){
        if(schoolScore != null) {
            if (TextUtils.isDigitsOnly(schoolScore.avgScoreReading)) {
                return Integer.parseInt(schoolScore.avgScoreReading) > BEST_READING_SCORE;
            }
        }
        return false;
    }

    public Map<String, SchoolScore> scoreMap = new HashMap<>();

    private MutableLiveData<List<SchoolScore>> schoolScores;
    public LiveData<List<SchoolScore>> getSchoolScore() {
        if (schoolScores == null) {
            schoolScores  = new MutableLiveData<>();
            populateSchoolScores();
        }
        return schoolScores;
    }

    private void populateSchoolList() {
        Call<List<SchoolDetails>> call = schoolRepository.getSchoolList();
        call.enqueue(new Callback<List<SchoolDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<SchoolDetails>> call, @NonNull Response<List<SchoolDetails>> response) {
                List<SchoolDetails> list = response.body();
                schoolList.postValue(list);
            }

            @Override
            public void onFailure(@NonNull Call<List<SchoolDetails>> call, @NonNull Throwable t) {
                //Handle failure
                Log.e("mandar", t.getLocalizedMessage());
                schoolList.postValue(null);
            }
        });

    }

    private void populateSchoolScores() {
        Call<List<SchoolScore>> call = schoolRepository.getSchoolScores();
        call.enqueue(new Callback<List<SchoolScore>>() {
            @Override
            public void onResponse(@NonNull Call<List<SchoolScore>> call, @NonNull Response<List<SchoolScore>> response) {
                List<SchoolScore> scores = response.body();
                refreshSchoolScores(scores);
            }

            @Override
            public void onFailure(@NonNull Call<List<SchoolScore>> call, @NonNull Throwable t) {
                //Handle failure
                Log.e("mandar", t.getLocalizedMessage());
                //dataLoadingError.postValue(Constants.ERROR_GENERIC_ERROR);
                refreshSchoolScores(null);
            }
        });

    }

    private void refreshSchoolScores(List<SchoolScore> scores){
        schoolScores.postValue(scores);
        scoreMap = getScoreMap(scores);
    }

    private Map<String, SchoolScore> getScoreMap(List<SchoolScore> list) {
        Map<String, SchoolScore> map = new HashMap<>();
        if(list != null && !list.isEmpty())
        for (SchoolScore score : list) {
            map.put(score.databaseNumber, score);
        }
        return map;
    }
}