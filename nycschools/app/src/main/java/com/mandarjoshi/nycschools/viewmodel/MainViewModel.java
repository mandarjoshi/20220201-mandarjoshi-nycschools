package com.mandarjoshi.nycschools.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
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

    @Inject
    SchoolRepository schoolRepository;

    private static final int BEST_READING_SCORE = 400;

    public MutableLiveData<List<SchoolDetails>> schoolList = new MutableLiveData<>();

    public void loadSchoolList() {
        if (schoolList.getValue() == null) {
            populateSchoolList();
        } else {
            schoolList.postValue(schoolList.getValue());
        }
    }

    public boolean isReadingBestScore(SchoolScore schoolScore){
        if(schoolScore != null) {
            if (TextUtils.isDigitsOnly(schoolScore.avgScoreReading)) {
                if (Integer.parseInt(schoolScore.avgScoreReading) > BEST_READING_SCORE) {
                    return true;
                }
            }
        }
        return false;
    }

    public MutableLiveData<List<SchoolScore>> schoolScores = new MutableLiveData<>();
    public Map<String, SchoolScore> scoreMap = new HashMap<>();

    public void loadSchoolScore() {
        if (schoolScores.getValue() == null) {
            populateSchoolScores();
        } else {
            schoolScores.postValue(schoolScores.getValue());
        }
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