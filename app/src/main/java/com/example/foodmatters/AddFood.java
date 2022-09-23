package com.example.foodmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFood extends AppCompatActivity {

    String name, amount, date, id;
    String oldName, oldAmount, oldDate;
    EditText nameV, amountV, dateV;
    Bundle editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        nameV = (EditText)findViewById(R.id.input_name);
        amountV = (EditText)findViewById(R.id.input_amount);
        dateV = (EditText)findViewById(R.id.input_date);

        editData = getIntent().getExtras();

        if (editData != null){
            id = editData.getString("id");
            oldName = editData.getString("name");
            oldAmount = editData.getString("amount");
            oldDate = editData.getString("expiration");

            nameV.setText(oldName);
            amountV.setText(oldAmount);
            dateV.setText(oldDate);
        }
    }

    public void submit(View view) {
        name = nameV.getText().toString();
        amount = amountV.getText().toString();
        date = dateV.getText().toString();

        Intent returnItem = new Intent();
        returnItem.putExtra("name", name);
        returnItem.putExtra("amount", amount);
        returnItem.putExtra("expiration", date);

        if (editData != null){
            //returnItem.putExtra("old_obj", new Food(oldName, oldAmount, oldDate));
            returnItem.putExtra("id", id);
            returnItem.putExtra("old_name", oldName);
            returnItem.putExtra("old_amount", oldAmount);
            returnItem.putExtra("old_date", oldDate);
        }

        setResult(RESULT_OK, returnItem);

        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
