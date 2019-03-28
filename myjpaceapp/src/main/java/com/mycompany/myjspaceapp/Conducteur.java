package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.Space;

public class Conducteur implements Runnable {

    private Space ts;
    private String[] codes;
    private float volume_reservoir;
    private int code_choisi;
    private String pompe_choisi;
    private String etat;

    public Conducteur(Space ts, String[] codes, float volume_reservoir, int code_choisi, String pompe_choisi,
            String etat) {
        this.ts = ts;
        this.codes = codes;
        this.volume_reservoir = volume_reservoir;
        this.code_choisi = code_choisi;
        this.pompe_choisi = pompe_choisi;
        this.etat = etat;
    }

    public void run() {

    }

}