package com.example.firebaseone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseone.databinding.ActivityChildrenBinding;
import com.example.firebaseone.models.ModelChild;
import com.example.firebaseone.models.ModelParent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChildrenActivity extends AppCompatActivity {

    // 1. identify listChildren
    ArrayList<ModelChild>listChildren = new ArrayList<>();

    // 2. identify Adapter of children
    AdapterChildren adapterChildren = new AdapterChildren();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ActivityChildrenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChildrenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String parentId = getIntent().getStringExtra(Const.PARENT_ID);
        assert parentId != null;
        ref.child(Const.KEY_CHILDREN).child(parentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    ModelChild m = snapshot1.getValue(ModelChild.class);
                    //3. add data on list
                    listChildren.add(snapshot1.getValue(ModelChild.class));
                }

                // 4. setList to adapter  5. setAdapter to recycler
                adapterChildren.setListChildren(listChildren);
                binding.recyclerChildren.setAdapter(adapterChildren);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //send Children data to fireBase
//         binding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                validateData(binding.childName.getText().toString().trim()
//                        ,binding.childAge.getText().toString().trim());
//            }
//        });

    }

    private void validateData(String name, String age) {
        if (name.isEmpty()) {
            Toast.makeText(this, "name is required", Toast.LENGTH_LONG).show();
        } else if (age.isEmpty()) {
            Toast.makeText(this, "age is required", Toast.LENGTH_LONG).show();

        } else {
            sendChildrenData(name, age);
        }

    }

    private void sendChildrenData(String name, String age) {
        String parentId = getIntent().getStringExtra(Const.PARENT_ID);
        // random id
        String id = ref.push().getKey();
        ref.child(Const.KEY_CHILDREN)
                .child(parentId)
                .child(id)
                .setValue(new ModelChild(name, age)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.childName.setText("");
                        binding.childAge.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChildrenActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}