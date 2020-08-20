package com.pc.android.program.sdk;

public enum PointCheckoutLanguage {
    ENGLISH("en"),
    ARABIC("ar");

    private String iso2;

    PointCheckoutLanguage(String iso2){
        this.iso2 = iso2;
    }

    public String getIso2(){
        return this.iso2;
    }
}
