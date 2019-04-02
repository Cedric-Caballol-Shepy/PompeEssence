package com.mycompany.myjspaceapp;

import java.util.Random;

public class CodeGenerator {
    private int n; //utilisé pour avoir un préfixe différent pour chaque code (au cas où)

    private CodeGenerator(){}

    private static class CodeGeneratorHolder{
        //classe utile pour n'avoir qu'une instance de CodeGenerator dans tout le programme
        //                                      sans problèmes de concurrence entre threads
        private static CodeGenerator instance = new CodeGenerator();
    }

    public static CodeGenerator getInstance(){
        return CodeGeneratorHolder.instance;
    }


    public String next(){
        this.n++;
        Random rand = new Random();
        int x = rand.nextInt(999999); // double check (si jamais deux codes arrivent à avoir le même n)
        return n + "c" + x; //comme ça on est sûr de ne pas avoir 2 fois le même code...
    }
}