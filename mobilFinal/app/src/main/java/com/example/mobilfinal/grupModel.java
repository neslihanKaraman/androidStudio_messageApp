package com.example.mobilfinal;

import java.util.List;

public class grupModel {
    String grupAdi, grupAciklamasi, grupResmi, kullanici, grupId;
    List<String> numara;
    public grupModel(String grupAdi, String grupAciklamasi, String grupResmi, String kullanici, String grupID, List<String> numara){
        this.grupAdi=grupAdi;
        this.grupAciklamasi=grupAciklamasi;
        this.grupId=grupID;
        this.grupResmi=grupResmi;
        this.kullanici=kullanici;
        this.numara=numara;
    }
    public String getGrupAdi(){

        return grupAdi;
    }

    public void setGrupAdi(String grupAdi) {

        this.grupAdi = grupAdi;
    }

    public void setGrupAciklamasi(String grupAciklamasi) {
        this.grupAciklamasi = grupAciklamasi;
    }
    public String getGrupAciklamasi() {
        return grupAciklamasi;
    }
    public String getGrupId() {
        return grupId;
    }

    public void setGrupId(String grupId) {
        this.grupId = grupId;
    }

    public String getGrupResmi() {
        return grupResmi;
    }

    public void setGrupResmi(String grupResmi) {
        this.grupResmi = grupResmi;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public List<String> getNumara() {
        return numara;
    }

    public void setNumara(List<String> numara) {
        this.numara = numara;
    }


}
