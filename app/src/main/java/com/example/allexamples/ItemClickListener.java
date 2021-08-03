package com.example.allexamples;

import android.view.View;

public interface ItemClickListener {
    void onItemClick(View v, int adapterPosition);
    void onLongItemClick(View v, int adapterPosition);
}
