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

public class rehberAdapter extends RecyclerView.Adapter<rehberAdapter.ContactViewHolder> {
    List<rehberModel> rehberModelList;
    tikla tikla1;

    public rehberAdapter(List<rehberModel> rehberModels,tikla tikla2){
        this.rehberModelList=rehberModels;
        this.tikla1=tikla2;
    }
    @NonNull
    @Override
    public rehberAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactViewHolder contactViewHolder=new ContactViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_rehber,parent,false));
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull rehberAdapter.ContactViewHolder holder, int position) {
        rehberModel rehberModel3=rehberModelList.get(position);
        holder.setData(rehberModel3);
    }

    @Override
    public int getItemCount() {
        return rehberModelList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView rehberResim;
        TextView rehberAd, rehberNo;
        public ContactViewHolder(View itemView) {
            super(itemView);
            rehberAd=itemView.findViewById(R.id.item_rehberAd);
            rehberNo=itemView.findViewById(R.id.item_rehberNumara);
            rehberResim=itemView.findViewById(R.id.item_rehberResim);

            itemView.setOnClickListener(this);
        }
        public void setData(rehberModel rehberModel){
            if(rehberModel.getResim()!=null){
                Picasso.get().load(rehberModel.getResim()).into(rehberResim);
            }
            rehberAd.setText(rehberModel.getAd());
            rehberNo.setText(rehberModel.getNumara());
        }

        @Override
        public void onClick(View view) {
            tikla1.tiklaGöster(getAdapterPosition());
        }
    }
}
