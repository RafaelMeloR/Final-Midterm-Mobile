package com.example.employeeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Use FragmentManager to add mainFragment to fragment to container

        FragmentManager fn = getSupportFragmentManager();
        Fragment fragment = fn.findFragmentById(R.id.fragment_container);
        if(fragment==null)
        {
            fragment = new MainFragment();
            fn.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_employee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        Intent intent;

        if(id == R.id.Emloyeeoptionitem1)
        {

            String location ="";
            int index = MainFragment.getCurrentIndex();
            Employee temp = MainFragment.all_employee.get(index);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
            HashMap hm= new HashMap<>();
            hm.put(""+temp.getEmp_id(),temp);
            databaseRef.child("Employee").updateChildren(hm);
            Toast.makeText(MainActivity.this, "Added to firebase", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.Emloyeeoptionitem2) {

            int index = MainFragment.getCurrentIndex();
            Employee temp = MainFragment.all_employee.get(index);
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Employee");
            databaseRef.child(String.valueOf(temp.getEmp_id())).removeValue();
            Toast.makeText(MainActivity.this, "Removed from firebase", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}