package com.mycompany.myjspaceapp;

import org.jspace.Space;

public abstract class PompeAbs implements Runnable{
    public String id;
    protected Space ts;

    PompeAbs(String id, Space ts){
        this.id = id;
        this.ts = ts;
    }
}
