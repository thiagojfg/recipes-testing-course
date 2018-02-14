package com.sqisland.tutorial.recipes.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by desen03 on 14/02/2018.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    public final TextView textView;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}
