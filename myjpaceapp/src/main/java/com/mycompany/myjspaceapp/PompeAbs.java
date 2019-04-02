package com.mycompany.myjspaceapp;

import org.jspace.Space;

public abstract class PompeAbs implements Runnable{
    public String id;
    private float volume_pompe;
    private Space ts;

    PompeAbs(String id, float volume_pompe, Space ts){
        this.id = id;
        this.volume_pompe = volume_pompe;
        this.ts = ts;
    }
}