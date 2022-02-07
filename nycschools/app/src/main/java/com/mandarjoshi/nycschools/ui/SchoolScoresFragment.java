package com.mandarjoshi.nycschools.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandarjoshi.nycschools.model.SchoolDetails;
import com.mandarjoshi.nycschools.ui.main.BaseFragment;
import com.mandarjoshi.nycschools.ui.main.MySchoolAdapter;
import com.mandarjoshi.nycschools.util.Constants;
import com.mandarjoshi.nycschools.NycSchoolApplication;
import com.mandarjoshi.nycschools.R;
import com.mandarjoshi.nycschools.databinding.FragmentSchoolScoresBinding;
import com.mandarjoshi.nycschools.model.SchoolScore;
import com.mandarjoshi.nycschools.util.DialogUtil;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

public class SchoolScoresFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel mainViewModel;
    private LiveData<List<SchoolScore>> schoolScores;

    private String id;
    private FragmentSchoolScoresBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity(),viewModelFactory).get(MainViewModel.class);
        ((NycSchoolApplication)requireActivity().getApplicationContext()).appComponent.inject(this);
        schoolScores = mainViewModel.getSchoolScore();
        schoolScores.observe(this,schoolScoreObserver);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_school_scores, container, false);
        id = getArguments().getString(Constants.SCHOOL_ID_KEY);
        showProgressBar(binding.getRoot());
        binding.setViewModel(mainViewModel);
        if(schoolScores.getValue() != null){
            binding.setScore(mainViewModel.scoreMap.get(id));
        }
        return binding.getRoot();
    }

    final Observer<List<SchoolScore>> schoolScoreObserver = new Observer<List<SchoolScore>>() {
        @Override
        public void onChanged(final List<SchoolScore> scores) {
            // Update the UI, in this case, a TextView.
            binding.getRoot().findViewById(R.id.progress_bar).setVisibility(View.GONE);
            if(scores == null) {
                DialogUtil.getSimpleErrorDialog(requireActivity()).show();
            } else {
                if(!scores.isEmpty()){
                    SchoolScore score = mainViewModel.scoreMap.get(id);
                    if (score != null) {
                        binding.setScore(score);
                    } else {
                        DialogUtil.getNodataDialog(requireActivity()).show();
                    }
                }
            }
            hideProgressBar(getView());
        }
    };


}