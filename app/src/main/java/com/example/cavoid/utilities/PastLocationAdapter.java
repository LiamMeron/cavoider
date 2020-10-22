package com.example.cavoid.utilities;

import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cavoid.R;

import java.util.ArrayList;

public class PastLocationAdapter extends RecyclerView.Adapter<PastLocationViewHolder>{
    ArrayList<String> pastLocationMessages;
    Context c;

    // Provide a suitable constructor (depends on the kind of dataset)
    public PastLocationAdapter(Context c, ArrayList<String> data) {
        this.c = c;
        pastLocationMessages = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PastLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        PastLocationViewHolder vh = new PastLocationViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PastLocationViewHolder vh, int position) {
        if((position+1) < pastLocationMessages.size()) {
            vh.pastCasesTextView.setText(pastLocationMessages.get(position));
            vh.pastDeathsTextView.setText(pastLocationMessages.get(position + 1));
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pastLocationMessages.size();
    }

}