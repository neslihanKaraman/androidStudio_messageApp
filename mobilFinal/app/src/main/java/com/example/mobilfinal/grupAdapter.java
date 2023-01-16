package com.example.mobilfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class grupAdapter extends RecyclerView.Adapter<grupAdapter.GroupViewHolder> {
    List<grupModel> grupModelList;
    public grupAdapter(List<grupModel> grupModelList){
        this.grupModelList=grupModelList;
    }

    @NonNull
    @Override
    public grupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupViewHolder groupViewHolder=new GroupViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grup_olustur,parent,false));
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull grupAdapter.GroupViewHolder holder, int position) {
        grupModel grupModel2=grupModelList.get(position);
        holder.setData(grupModel2);

    }
    @Override
    public int getItemCount() {
        return grupModelList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView ad, aciklama;
        ImageView fotograf;
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            ad=itemView.findViewById(R.id.item_grupOlustur_grupAdi);
            aciklama=itemView.findViewById(R.id.item_grupOlustur_aciklama);
            fotograf=itemView.findViewById(R.id.item_grupOlustur_resim);
        }
        public void setData(grupModel grupModel2) {
            ad.setText(grupModel2.getGrupAdi());
            aciklama.setText(grupModel2.getGrupAciklamasi());

            if(grupModel2.grupResmi!=null){
                Picasso.get().load(grupModel2.getGrupResmi()).into(fotograf);
            }
        }
    }
}
