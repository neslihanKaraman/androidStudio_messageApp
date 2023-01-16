package com.example.mobilfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mesajAdapter extends RecyclerView.Adapter<mesajAdapter.MessageViewHolder> {
    List<mesajModel> mesajModelList;
    public mesajAdapter(List<mesajModel> mesajModelList){
        this.mesajModelList=mesajModelList;
    }
    @NonNull
    @Override
    public mesajAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesaj_olustur,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mesajAdapter.MessageViewHolder holder, int position) {
        mesajModel mesajModel=mesajModelList.get(position);
        holder.setData(mesajModel);

    }

    @Override
    public int getItemCount() {
        return mesajModelList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mesaj,aciklama;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            mesaj=itemView.findViewById(R.id.item_mesajOlustur_mesajAdi);
            aciklama= itemView.findViewById(R.id.item_mesajOlustur_aciklama);
        }
        public void setData(mesajModel mesajModel2){
            mesaj.setText(mesajModel2.getMesajAdi());
            aciklama.setText(mesajModel2.getMesajAciklamasi());
        }
    }
}
