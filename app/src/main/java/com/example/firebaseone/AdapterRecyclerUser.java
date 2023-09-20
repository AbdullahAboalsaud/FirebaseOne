package com.example.firebaseone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseone.models.ModelParent;

import java.util.ArrayList;

public class AdapterRecyclerUser extends RecyclerView.Adapter<AdapterRecyclerUser.MyHolder> {

    private ArrayList<ModelParent> list;

    public void setList(ArrayList<ModelParent> list) {
        this.list = list;
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        ModelParent modelParent = list.get(position);
        holder.bind(modelParent);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private TextView userPhone;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick!=null)
                        onItemClick.onClick(list.get(getLayoutPosition()).getId());
                }
            });
            userName = itemView.findViewById(R.id.rec_user_name);
            userPhone = itemView.findViewById(R.id.rec_user_phone);
        }

        public void bind(ModelParent model) {
            userName.setText(model.getName());
            userPhone.setText(model.getPhone());
        }
    }


    interface OnItemClick{
         void onClick(String id);
    }

}
