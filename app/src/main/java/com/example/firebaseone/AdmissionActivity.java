package com.example.firebaseone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebaseone.databinding.ActivityAdmissionBinding;
import com.example.firebaseone.models.ModelParent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdmissionActivity extends AppCompatActivity {
    ActivityAdmissionBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    // 1. identify list of parents
    ArrayList<ModelParent> listParents = new ArrayList<>();

    // 2. identify Adapter
    AdapterRecyclerUser adapterRecyclerUser = new AdapterRecyclerUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdmissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();

        adapterRecyclerUser.setOnItemClick(new AdapterRecyclerUser.OnItemClick() {
            @Override
            public void onClick(String id) {
                Intent intent = new Intent(AdmissionActivity.this,ChildrenActivity.class);
                intent.putExtra(Const.PARENT_ID,id);
                startActivity(intent);
            }
        });

    }

    private void getData() {
        ref.child(Const.KEY_PARENTS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listParents.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    listParents.add(snapshot1.getValue(ModelParent.class));
                }

                // 3. setList to adapter  4. setAdapter to recycler
                adapterRecyclerUser.setList(listParents);
                binding.recycler.setAdapter(adapterRecyclerUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}