package com.example.foodmatters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView r1;
    String dummyNames[], dummyAmounts[], dummyExpirations[];
    //ArrayList<String> foods = new ArrayList<String>();
    //ArrayList<String> amounts = new ArrayList<String>();
    //ArrayList<String> expirations = new ArrayList<String>();

    ArrayList<Food> foods = new ArrayList<Food>();

    Adapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = (RecyclerView) findViewById(R.id.recycleView);

        dummyAmounts = getResources().getStringArray(R.array.amounts);
        dummyNames = getResources().getStringArray(R.array.foods);
        dummyExpirations = getResources().getStringArray(R.array.expiry_dates);

        // Add dummy values to corresponding array lists
        for (int i = 0; i < dummyNames.length; i++){
            foods.add(new Food(dummyNames[i], dummyAmounts[i], dummyExpirations[i]));
        }

        ad = new Adapter(this, foods);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(r1);
    }

    public void addItem(View view) {
        Intent i1 = new Intent(this, AddFood.class);
        startActivityForResult(i1, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();

                Food justAdded = new Food(extras.getString("name"), extras.getString("amount"), extras.getString("expiration"));

                foods.add(justAdded);

                ad = new Adapter(this, foods);
                r1.setAdapter(ad);
                r1.setLayoutManager(new LinearLayoutManager(this));

                //mRecyclerView = view.findViewById(R.id.recycleView);
                //registerForContextMenu(mRecyclerView);
            }
        }else if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Bundle extras = data.getExtras();

                for (Food item : foods){
                    if (item.getId() == Integer.parseInt(extras.getString("id"))){
                        item.setName(extras.getString("name"));
                        item.setAmount(extras.getString("amount"));
                        item.setExpirationDate(extras.getString("expiration"));
                    }
                }

                ad = new Adapter(this, foods);
                r1.setAdapter(ad);
                r1.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }
}
