package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class RemplisseurPompe extends PompeAbs{

    public int id;
    private float volume_pompe;
    private Space ts;

    RemplisseurPompe(int id, float volume_pompe, Space ts){
        super(id,volume_pompe,ts);
    }

    @Override
    public void run() {
        try {
            Object volume_essence = ts.get(new ActualField(id), new FormalField(Float.class));
            volume_pompe += (float) volume_essence;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
