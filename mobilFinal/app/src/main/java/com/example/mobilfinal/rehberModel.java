package com.example.mobilfinal;

public class rehberModel {
    private String ad,numara,resim;
    public rehberModel(String ad, String numara,String resim){
        this.ad=ad;
        this.numara=numara;
        this.resim=resim;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
