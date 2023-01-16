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

public class uyeGrupAdapter extends RecyclerView.Adapter<uyeGrupAdapter.GroupViewHolder> {
    List<grupModel> grupModelList;
    tikla tikla1;
    public uyeGrupAdapter(List<grupModel> grupModelList, tikla tikla2){
        this.grupModelList=grupModelList;
        this.tikla1=tikla2;
    }

    @NonNull
    @Override
    public uyeGrupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupViewHolder groupViewHolder=new GroupViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grup_olustur,parent,false),tikla1);
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        grupModel grupModel2=grupModelList.get(position);
        holder.setData(grupModel2);
    }

    @Override
    public int getItemCount() {
        return grupModelList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView ad, aciklama;
        ImageView fotograf;
        tikla tikla3;

        public GroupViewHolder(@NonNull View itemView, tikla tikla1) {
            super(itemView);
            ad=itemView.findViewById(R.id.item_grupOlustur_grupAdi);
            aciklama=itemView.findViewById(R.id.item_grupOlustur_aciklama);
            fotograf=itemView.findViewById(R.id.item_grupOlustur_resim);
            this.tikla3=tikla1;
            itemView.setOnClickListener(this);
        }
        public void setData(grupModel grupModel2) {
            ad.setText(grupModel2.getGrupAdi());
            aciklama.setText(grupModel2.getGrupAciklamasi());


            if(grupModel2.grupResmi!=null){
                Picasso.get().load(grupModel2.getGrupResmi()).into(fotograf);
            }
        }

        @Override
        public void onClick(View view) {
            tikla3.tiklaGöster(getAdapterPosition());
        }
    }
}
