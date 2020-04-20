package com.example.help.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.help.R;
import com.example.help.models.Uinfo;

import java.util.List;

public class UinfoAdapter extends RecyclerView.Adapter<UinfoAdapter.UinfoViewHolder> {

    private List<Uinfo> datas;

    public UinfoAdapter(List<Uinfo> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public UinfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UinfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UinfoViewHolder holder, int position) {
        Uinfo data = datas.get(position);
        holder.name.setText(data.getName());
        holder.height.setText(data.getHeight());
        holder.weight.setText(data.getWeight());
        holder.birthdate.setText(data.getBirthdate());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class UinfoViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView height;
        private TextView weight;
        private TextView birthdate;

        public UinfoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_uinfo_name);
            height = itemView.findViewById(R.id.item_uinfo_height);
            weight = itemView.findViewById(R.id.item_uinfo_weight);
            birthdate = itemView.findViewById(R.id.item_uinfo_birthdate);




        }
    }
}
