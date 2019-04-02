package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class RemplisseurPompe extends PompeAbs{

    public String id;
    private float volume_pompe;
    private Space ts;

    RemplisseurPompe(String id, float volume_pompe, Space ts){
        super(id,volume_pompe,ts);
    }

    @Override
    public void run() {
        try {
            Object volume_essence = ts.get(new ActualField(id), new FormalField(Float.class));
            System.out.println("RemplisseurPompe : recupere le volume d'essence : " + (float)volume_essence);
            volume_pompe += (float) volume_essence;
            System.out.println("RemplisseurPompe : la pompe se remplit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
