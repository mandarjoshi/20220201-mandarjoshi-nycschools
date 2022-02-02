package com.mandarjoshi.nycschools.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandarjoshi.nycschools.NycSchoolApplication;
import com.mandarjoshi.nycschools.util.Constants;
import com.mandarjoshi.nycschools.R;
import com.mandarjoshi.nycschools.model.SchoolDetails;
import com.mandarjoshi.nycschools.util.DialogUtil;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SchoolFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_list, container, false);

        // Set the adapter

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.school_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainViewModel mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        ((NycSchoolApplication)requireActivity().getApplicationContext()).appComponent.inject(mViewModel);
        showProgressBar(view);
        mViewModel.schoolList.observe(this,schoolListObserver);
        mViewModel.loadSchoolList();
        mViewModel.loadSchoolScore();
        return view;
    }

    private void navigateToScores(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SCHOOL_ID_KEY, id);
        NavHostFragment.findNavController(this).navigate(R.id.navigate_to_school_scores, bundle);
    }

    final Observer<List<SchoolDetails>> schoolListObserver = new Observer<List<SchoolDetails>>() {
        @Override
        public void onChanged(final List<SchoolDetails> list) {
            // Update the UI, in this case, a TextView.
            if(list == null){
                DialogUtil.getSimpleErrorDialog(requireActivity()).show();
            } else if(list.isEmpty()){
                DialogUtil.getNodataDialog(requireActivity()).show();
            } else{
                recyclerView.setAdapter(new MySchoolAdapter(list, id -> navigateToScores(id)));
            }
            hideProgressBar(getView());
        }
    };



}