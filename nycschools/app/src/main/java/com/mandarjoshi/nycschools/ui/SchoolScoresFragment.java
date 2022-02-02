package com.mandarjoshi.nycschools.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandarjoshi.nycschools.ui.main.BaseFragment;
import com.mandarjoshi.nycschools.util.Constants;
import com.mandarjoshi.nycschools.NycSchoolApplication;
import com.mandarjoshi.nycschools.R;
import com.mandarjoshi.nycschools.databinding.FragmentSchoolScoresBinding;
import com.mandarjoshi.nycschools.model.SchoolScore;
import com.mandarjoshi.nycschools.util.DialogUtil;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;

import java.util.List;

public class SchoolScoresFragment extends BaseFragment {

    private MainViewModel mViewModel;

    private String id;
    private FragmentSchoolScoresBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_school_scores, container, false);
        id = getArguments().getString(Constants.SCHOOL_ID_KEY);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        ((NycSchoolApplication)requireActivity().getApplicationContext()).appComponent.inject(mViewModel);
        showProgressBar(binding.getRoot());
        mViewModel.schoolScores.observe(this,schoolScoreObserver);
        binding.setViewModel(mViewModel);
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
                    SchoolScore score = mViewModel.scoreMap.get(id);
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