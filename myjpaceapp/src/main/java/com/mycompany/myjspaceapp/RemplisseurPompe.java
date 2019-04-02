package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class RemplisseurPompe extends PompeAbs{

    RemplisseurPompe(String id, float volume_pompe, Space ts){
        super(id,volume_pompe,ts);
    }

    @Override
    public void run() {
        try {
            Object[] volume_essence = ts.get(new ActualField(id), new FormalField(Float.class));
            System.out.println("RemplisseurPompe : recupere le volume d'essence : " + volume_essence[1]);
            volume_pompe += (float) volume_essence[1];
            System.out.println("RemplisseurPompe : la pompe se remplit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
