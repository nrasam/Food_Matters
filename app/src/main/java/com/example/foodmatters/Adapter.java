package com.example.foodmatters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

    ArrayList<Food> foods;
    Context ctx;

    public Adapter(Context ct, ArrayList<Food> foods){
        ctx = ct;
        this.foods = foods;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(ctx);
        View myView = inflator.inflate(R.layout.my_row, parent, false);

        return new MyHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.t1.setText(this.foods.get(position).getId() + "");
        holder.t2.setText(this.foods.get(position).getName());
        holder.t3.setText(this.foods.get(position).getAmount());
        holder.t4.setText(this.foods.get(position).getExpirationDate());
    }

    @Override
    public int getItemCount() {
        return this.foods.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView t1, t2, t3, t4;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            t1 = (TextView)itemView.findViewById(R.id.item_number);
            t2 = (TextView)itemView.findViewById(R.id.item_name);
            t3 = (TextView)itemView.findViewById(R.id.item_amount);
            t4 = (TextView)itemView.findViewById(R.id.item_expiry);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
            //menu.setHeaderTitle("Select the Action");
            //menu.add(0, itemView.getId(), 0, "Edit Item");
            //menu.add(0, itemView.getId(), 0, "Remove Item");
            //getMenuInflater().inflate(R.menu.menu_context, menu);

            menu.add("Edit Item").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent editItem = new Intent(ctx.getApplicationContext(), AddFood.class);
                    editItem.putExtra("id", t1.getText().toString());
                    editItem.putExtra("name", t2.getText().toString());
                    editItem.putExtra("amount", t3.getText().toString());
                    editItem.putExtra("expiration", t4.getText().toString());

                    ((Activity) ctx).startActivityForResult(editItem, 1);

                    return true;
                }
            });

            menu.add("Remove Item").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    String key = t2.getText().toString();
                    Toast.makeText(ctx, key, Toast.LENGTH_SHORT).show();
/*
                    int pos = data1.length + 1;
                    for (int i = 0; i < data1.length; i++){
                        if (data1[i].equals(key)){
                            pos = i;
                        }
                    }

                    String tmp[] = new String[data1.length - 1];
                    for (int i = 0; i < tmp.length; i++){
                        if (i >= pos){
                            tmp[i] = data1[i + 1];
                        }else{
                            tmp[i] = data1[i];
                        }
                    }

                    data1 = tmp;*/

                    int pos = getAdapterPosition();
                    foods.remove(pos);

                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, foods.size());
                    return true;
                }
            });
        }
    }
}
