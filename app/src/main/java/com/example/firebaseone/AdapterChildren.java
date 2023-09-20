package com.example.firebaseone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseone.models.ModelChild;

import java.util.ArrayList;

public class AdapterChildren extends RecyclerView.Adapter<AdapterChildren.HolderChildren> {

    ArrayList<ModelChild> listChildren;

    public void setListChildren(ArrayList<ModelChild> listChildren) {
        this.listChildren = listChildren;
    }


    @NonNull
    @Override
    public HolderChildren onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_children,parent,false);
        return new HolderChildren(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderChildren holder, int position) {

        ModelChild modelChild = listChildren.get(position);
        holder.bind(modelChild);

    }

    @Override
    public int getItemCount() {
        return listChildren != null ? listChildren.size() : 0;
    }

    class HolderChildren extends RecyclerView.ViewHolder {

        private TextView childName;
        private TextView childAge;
        public HolderChildren(@NonNull View itemView) {
            super(itemView);

            childName = itemView.findViewById(R.id.rec_child_name);
            childAge = itemView.findViewById(R.id.rec_child_age);
        }

        public void bind(ModelChild model){
            childName.setText(model.getName());
            childAge.setText(model.getAge());
        }

    }


}
