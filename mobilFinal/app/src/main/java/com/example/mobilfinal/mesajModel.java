package com.example.mobilfinal;

public class mesajModel {
    String mesajAdi, mesajAciklamasi, Id;
    public mesajModel(String mesajAdi, String mesajAciklamasi,String Id){
        this.mesajAdi=mesajAdi;
        this.mesajAciklamasi=mesajAciklamasi;
        this.Id=Id;
    }

    public String getMesajAdi() {
        return mesajAdi;
    }

    public void setMesajAdi(String mesajAdi) {
        this.mesajAdi = mesajAdi;
    }

    public String getMesajAciklamasi() {
        return mesajAciklamasi;
    }

    public void setMesajAciklamasi(String mesajAciklamasi) {
        this.mesajAciklamasi = mesajAciklamasi;
    }

    public void setId(String id) {
        Id = Id;
    }

    public String getId() {
        return Id;
    }
}
