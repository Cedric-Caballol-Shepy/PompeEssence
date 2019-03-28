package com.mycompany.myjspaceapp;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class StationEssence {

    public static void main(String[] argv) throws InterruptedException {
        Space inbox = new SequentialSpace();

        inbox.put("Hello World!");
        Object[] tuple = inbox.get(new FormalField(String.class));
        System.out.println(tuple[0]);

    }

}