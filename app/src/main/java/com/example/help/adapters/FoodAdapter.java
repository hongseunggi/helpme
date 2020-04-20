package com.example.help.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.help.R;
import com.example.help.models.food_info;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<food_info> datas;


    public FoodAdapter(List<food_info> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        food_info data = datas.get(position);
        holder.eat_time.setText(data.getEat_time());
        holder.food_name.setText(data.getFood_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        private TextView eat_time;
        private TextView food_name;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            eat_time = itemView.findViewById(R.id.item_eat_time);
            food_name = itemView.findViewById(R.id.item_food_name);


        }
    }
}
