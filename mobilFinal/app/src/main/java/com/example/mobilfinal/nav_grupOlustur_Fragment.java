package com.example.mobilfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class nav_grupOlustur_Fragment extends Fragment {
    EditText grupAdii,grupAciklamasi;
    ImageView grupResmi;
    Button grupOlustur_btn;
    RecyclerView recyclerView;

    Uri resim;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;

    ArrayList<grupModel> grupModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_nav_grup_olustur_, container, false);
        grupAdii=root.findViewById(R.id.et_grupAdi_grupOlustur);
        grupAciklamasi=root.findViewById(R.id.et_aciklama_grupOlustur);
        grupResmi=root.findViewById(R.id.iw_grupSimgesi_grupOlustur);
        grupOlustur_btn=root.findViewById(R.id.btn_grupOlustur);
        recyclerView=root.findViewById(R.id.rw_gruplar_grupOlustur);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        grupModelArrayList=new ArrayList<>();

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result -> {
                    if(result.getResultCode()==getActivity().RESULT_OK){
                        Intent data=result.getData();
                        resim=data.getData();
                        grupResmi.setImageURI(resim);
                    }
                }
        );
        grupResmi.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setType("resimler/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
        });

        grupOlustur_btn.setOnClickListener(view -> {
            String grupAdi_buton=grupAdii.getText().toString();
            String grupAciklamasi_buton=grupAciklamasi.getText().toString();
            if(grupAdi_buton.isEmpty()){
                Toast.makeText(getContext(), "Grup adı boş olamaz", Toast.LENGTH_SHORT).show();
                return;
            }
            if(grupAciklamasi_buton.isEmpty()){
                Toast.makeText(getContext(), "Grup açıklaması boş olamaz", Toast.LENGTH_SHORT).show();
                return;
            }
            if(resim!=null){
                StorageReference storageReference=firebaseStorage.getReference().child("resimler" + UUID.randomUUID().toString());
                storageReference.putFile(resim).addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String url=uri.toString();
                        Toast.makeText(getContext(), "resim başarıyla yüklendi", Toast.LENGTH_SHORT).show();
                        grupOlustur(grupAdi_buton,grupAciklamasi_buton,url);
                    });
                });
            }
            else{
                grupOlustur(grupAdi_buton,grupAciklamasi_buton,null);
            }
        });
        grupGöster();
        return root;
    }
    private void grupOlustur(String ad,String aciklama, String resim){
        String user=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+user+"/groups").add(new HashMap<String, Object>(){{
            put("name", ad);
            put("description", aciklama);
            put("image", resim);
            put("number", new ArrayList<String>());
        }}).addOnSuccessListener(documentReference -> {
            Toast.makeText(getContext(), "grup oluştu", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "grup oluşturulamadı", Toast.LENGTH_SHORT).show();
        });
    }
    private void grupGöster(){
        String userID=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("/userdata/"+userID+"/groups").get().addOnSuccessListener(queryDocumentSnapshots -> {
            grupModelArrayList.clear();
            for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments()){
                grupModel grupModel2=new grupModel(documentSnapshot.getString("name"),
                        documentSnapshot.getString("description"),
                        documentSnapshot.getString("image"),
                        documentSnapshot.getString("kullanici"),
                        documentSnapshot.getString("grupID"),
                        (List<String>)documentSnapshot.get("number"));
                grupModelArrayList.add(grupModel2);
                recyclerView.setAdapter(new grupAdapter(grupModelArrayList));
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
}