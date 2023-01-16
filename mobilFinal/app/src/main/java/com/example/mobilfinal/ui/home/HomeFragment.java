package com.example.mobilfinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilfinal.R;
import com.example.mobilfinal.databinding.FragmentHomeBinding;
import com.example.mobilfinal.grupAdapter;
import com.example.mobilfinal.grupModel;
import com.example.mobilfinal.mesajAdapter;
import com.example.mobilfinal.mesajModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {
    EditText mesajAdi_et,mesajAciklamasi_et;
    RecyclerView recyclerView;
    Button mesajOlustur_btn;
    RecyclerView recyclerView2;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    ArrayList<mesajModel>mesajModelArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        mesajAdi_et=root.findViewById(R.id.et_mesajAdi_mesajOlustur);
        mesajAciklamasi_et=root.findViewById(R.id.et_mesaj_mesajOlustur);
        mesajOlustur_btn=root.findViewById(R.id.btn_mesajOlustur);
        recyclerView2=root.findViewById(R.id.rw_mesajlar_mesajOlustur);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        mesajModelArrayList=new ArrayList<>();

        mesajOlustur_btn.setOnClickListener(view -> {
            String mesajAdi=mesajAdi_et.getText().toString();
            String mesajAciklamasi=mesajAciklamasi_et.getText().toString();
            if(mesajAdi.isEmpty()){
                Toast.makeText(getContext(), "Mesaj adı boş olamaz", Toast.LENGTH_SHORT).show();
                return;
            }
            if(mesajAciklamasi.isEmpty()){
                Toast.makeText(getContext(), "Mesaj açıklaması boş olamaz", Toast.LENGTH_SHORT).show();
                return;
            }
            mesajOlustur(mesajAdi,mesajAciklamasi);
        });
        mesajGöster();
        return root;
    }
    private void mesajOlustur(String mesaj, String aciklama){
        String user=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+user+"/messages").add(new HashMap<String, Object>(){{
            put("name", mesaj);
            put("description", aciklama);
        }}).addOnSuccessListener(documentReference -> {
            Toast.makeText(getContext(), "mesaj oluştu", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "mesaj oluşturulamadı", Toast.LENGTH_SHORT).show();
        });
    }
    private void mesajGöster(){
        String userID=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+userID+"/messages").get().addOnSuccessListener(queryDocumentSnapshots -> {
            mesajModelArrayList.clear();
            for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments()){
                mesajModel mesajModel2=new mesajModel(documentSnapshot.getString("name"),
                        documentSnapshot.getString("description"),
                        documentSnapshot.getString("id"));
                        mesajModelArrayList.add(mesajModel2);

                recyclerView2.setAdapter(new mesajAdapter(mesajModelArrayList));
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView2.setLayoutManager(linearLayoutManager);
            }
        });
    }
}