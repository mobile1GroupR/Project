package com.example.mobilelocker;

import java.util.ArrayList;

public class Symbols {

    private int idCounter=0;
    private int id;
    private String symbolsSet;

    public Symbols( String symbolsSet) {
        this.id = idCounter;
        this.symbolsSet = symbolsSet;
        idCounter++;
    }

    public int getId() {
        return id;
    }


}
