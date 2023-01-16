package com.example.mobilfinal;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class nav_mesajGonder_Fragment extends Fragment {
    RecyclerView grupRv,mesajRv;
    TextView secilenMesaj,secilenGrup;
    Button gonder;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    ArrayList<grupModel>grupModelArrayList;
    ArrayList<mesajModel>mesajModelArrayList;

    grupModel secilenGrupModel;
    mesajModel secilenMesajModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_nav_mesaj_gonder_, container, false);
        grupRv=root.findViewById(R.id.mesajGonderGrup_rv);
        mesajRv=root.findViewById(R.id.mesajGonderMesaj_rv);
        secilenMesaj=root.findViewById(R.id.mesajGonder_secilenMesaj_tv);
        secilenGrup=root.findViewById(R.id.mesajGonderGrup_secilenGrup_tv);
        gonder=root.findViewById(R.id.mesajGonder_Button);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        grupModelArrayList=new ArrayList<>();
        mesajModelArrayList=new ArrayList<>();


        ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGrant -> {
            if (isGrant) {
                MesajGonder();
            }else{
                Toast.makeText(getContext(), "Mesaj için izin gereklidir", Toast.LENGTH_SHORT).show();
            }
        });
        gonder.setOnClickListener(view -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(android.Manifest.permission.SEND_SMS) != android.content.pm.PackageManager.PERMISSION_GRANTED){
                launcher.launch(android.Manifest.permission.SEND_SMS);
            }else{
                MesajGonder();
            }
        });

        mesajGöster();
        grupGöster();
        return root;

    }
    private void grupGöster(){
        String user=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+user+"/groups").get().addOnCompleteListener(result ->{
            if(result.isSuccessful()){
                grupModelArrayList.clear();
                for(DocumentSnapshot document:result.getResult()){
                    grupModel grupModel2 = new grupModel(document.getString("name"),
                            document.getString("description"),
                            document.getString("image"),
                            document.getString("kullanici"),
                            document.getString("grupID"),
                            (List<String>) document.get("number"));
                    grupModelArrayList.add(grupModel2);
                }
                grupRv.setAdapter(new uyeGrupAdapter(grupModelArrayList, view->{
                    secilenGrupModel=grupModelArrayList.get(view);
                    secilenGrup.setText("Seçilen grup: "+secilenGrupModel.getGrupAdi());
                }));
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                grupRv.setLayoutManager(linearLayoutManager);
            }
        });
    }
    private void mesajGöster(){
        String user=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+user+"/messages").get().addOnCompleteListener(result->{
            if(result.isSuccessful()){
                mesajModelArrayList.clear();
                for(DocumentSnapshot document: result.getResult()){
                    mesajModel mesajModel1=new mesajModel(document.getString("name"),
                            document.getString("description"),document.getId());
                    mesajModelArrayList.add(mesajModel1);
                }
                mesajRv.setAdapter(new mesajGonderAdapter(mesajModelArrayList,position ->{
                    secilenMesajModel=mesajModelArrayList.get(position);
                    secilenMesaj.setText("Seçilen mesaj: "+ secilenMesajModel.getMesajAdi());
                }));
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
                mesajRv.setLayoutManager(linearLayoutManager);
            }
        });
    }
    private void MesajGonder(){
        if(secilenGrupModel.getNumara()!=null && secilenGrupModel.getNumara().size()>0){
            SmsManager smsManager=SmsManager.getDefault();
            for(String number: secilenGrupModel.getNumara()){
                smsManager.sendTextMessage(number,null,secilenMesajModel.getMesajAciklamasi(),null,null);
            }
            Toast.makeText(getContext(),"Mesaj gönderldi.",Toast.LENGTH_SHORT).show();
        }
        }
}