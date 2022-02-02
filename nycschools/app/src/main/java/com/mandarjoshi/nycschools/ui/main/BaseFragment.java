package com.mandarjoshi.nycschools.ui.main;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.mandarjoshi.nycschools.R;

public class BaseFragment extends Fragment {

    protected void showProgressBar(View parentView){
        parentView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    protected void hideProgressBar(View parentView){
        parentView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }
}
