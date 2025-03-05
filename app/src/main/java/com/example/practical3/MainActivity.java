package com.example.practical3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView. OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String myConcertText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), myConcertText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner myConcertSpinner = findViewById(R.id.concertSpinner);
        ArrayAdapter<CharSequence> myConcertAdapter =  ArrayAdapter.createFromResource(this, R.array.concertNames, android.R.layout.simple_spinner_item);
        myConcertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myConcertSpinner.setAdapter(myConcertAdapter);
        myConcertSpinner.setOnItemSelectedListener(this);
    }
}