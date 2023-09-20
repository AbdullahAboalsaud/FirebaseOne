package com.example.firebaseone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseone.databinding.ActivityMainBinding;
import com.example.firebaseone.models.ModelParent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData(binding.userName.getText().toString().trim()
                        ,binding.userEmail.getText().toString().trim()
                        ,binding.userPhone.getText().toString().trim());
            }
        });
    }


    private void validateData(String name, String email, String phone){
        if (name.isEmpty()){
            Toast.makeText(this,"name is required", Toast.LENGTH_LONG).show();
        }else if (email.isEmpty()){
            Toast.makeText(this,"email is required", Toast.LENGTH_LONG).show();
        }else if(phone.isEmpty()){
            Toast.makeText(this,"phone is required", Toast.LENGTH_LONG).show();
        }else{
            sendParentData(name,email,phone);
        }

    }
    private void sendParentData(String name, String email, String phone) {
        // random id
        String id = ref.push().getKey();
        assert id != null;
        ref.child(Const.KEY_PARENTS)
                .child(id)
                .setValue(new ModelParent(name, email, phone, id)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.userName.setText("");
                        binding.userEmail.setText("");
                        binding.userPhone.setText("");
                        Intent intent = new Intent(MainActivity.this,ChildrenActivity.class);
                        intent.putExtra(Const.PARENT_ID,id);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}