package com.example.practical3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView. OnItemSelectedListener {
    double pricePerTicket = 79.99;
    int totalNumberOfTickets;
    double totalPrice;
    String itemChoice;
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


        final EditText numberOfTickets = (EditText) findViewById(R.id.txtNumberOfTickets);

        Button calculateTotalCost = (Button) findViewById(R.id.btnCost);

        calculateTotalCost.setOnClickListener(new View.OnClickListener() {
            final TextView totalCost = ((TextView) findViewById(R.id.txtTotalCostDisplay));
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                totalNumberOfTickets = Integer.parseInt(numberOfTickets.getText().toString());
                totalPrice = totalNumberOfTickets * pricePerTicket;
                DecimalFormat currency = new DecimalFormat("R###,###.##");
                itemChoice = myConcertSpinner.getSelectedItem().toString();
                totalCost.setText("The total cost for the " + itemChoice + " concert tickets is " + currency.format(totalPrice));
            }
        });
    }
}