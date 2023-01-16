package com.example.mobilfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mesajGonderAdapter extends RecyclerView.Adapter<mesajGonderAdapter.MessageViewHolder> {
    List<mesajModel> mesajModelList;
    tikla t;

    public mesajGonderAdapter(List<mesajModel> mesajModelList, tikla t1){
        this.mesajModelList=mesajModelList;
        this.t=t1;
    }
    @NonNull
    @Override
    public mesajGonderAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesaj_gonder,parent,false),t);
    }

    @Override
    public void onBindViewHolder(@NonNull mesajGonderAdapter.MessageViewHolder holder, int position) {
        mesajModel mm=mesajModelList.get(position);
        holder.setData(mm);

    }

    @Override
    public int getItemCount() {
        return mesajModelList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mesaj, aciklama;
        tikla t2;
        public MessageViewHolder(@NonNull View itemView, tikla t) {
            super(itemView);
            mesaj=itemView.findViewById(R.id.itemmesajGonder_mesajAdi);
            aciklama=itemView.findViewById(R.id.item_mesajGönder_aciklama);
            this.t2=t;
            itemView.setOnClickListener(this);
        }
        public void setData(mesajModel mesajModel){
            mesaj.setText(mesajModel.getMesajAdi());
            aciklama.setText(mesajModel.getMesajAciklamasi());
        }

        @Override
        public void onClick(View view) {
            t.tiklaGöster(getAdapterPosition());

        }
    }
}
