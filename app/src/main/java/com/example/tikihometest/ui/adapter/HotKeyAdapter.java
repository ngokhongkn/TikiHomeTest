package com.example.tikihometest.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tikihometest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotKeyAdapter extends RecyclerView.Adapter<HotKeyAdapter.ViewHolder> {
    private List<String> listKeyWord;

    public HotKeyAdapter() {
        this.listKeyWord = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View item = inflater.inflate(R.layout.layout_card_key_word, viewGroup, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemText.setText(listKeyWord.get(i));
        viewHolder.itemCard.setCardBackgroundColor(getRandomColor());

    }

    public void setItems(List<String> items) {
        listKeyWord.clear();
        listKeyWord.addAll(items);
        notifyDataSetChanged();
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public int getItemCount() {
        return listKeyWord.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        CardView itemCard;

        ViewHolder(View view) {
            super(view);
            itemText = view.findViewById(R.id.item_text);
            itemCard = view.findViewById(R.id.item_card);

        }
    }
}
