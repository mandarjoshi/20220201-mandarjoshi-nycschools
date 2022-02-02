package com.mandarjoshi.nycschools.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandarjoshi.nycschools.databinding.FragmentSchoolBinding;
import com.mandarjoshi.nycschools.model.SchoolDetails;

import java.util.List;

public class MySchoolAdapter extends RecyclerView.Adapter<MySchoolAdapter.ViewHolder> {

    private final List<SchoolDetails> mValues;
    private final OnItemClickListener onItemClickListener;

    public MySchoolAdapter(List<SchoolDetails> items, OnItemClickListener onItemClickListener) {
        mValues = items;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(this.onItemClickListener, FragmentSchoolBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.schoolName.setText(holder.mItem.schoolName);
        holder.neighbourhood.setText(holder.mItem.neighbourHood);
        holder.id = holder.mItem.databaseNumber;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView schoolName;
        public final TextView neighbourhood;
        public String id;
        public SchoolDetails mItem;

        public ViewHolder(OnItemClickListener onItemClickListener, FragmentSchoolBinding binding) {
            super(binding.getRoot());
            schoolName = binding.itemNumber;
            neighbourhood = binding.content;
            binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(id));
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + neighbourhood.getText() + "'";
        }
    }
}