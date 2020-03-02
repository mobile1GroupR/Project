package com.example.mobilelocker;

import android.util.Log;

import java.util.ArrayList;

public class CurrentSymbols {

    public final static String greekSymbols = "λξζσφψγμδ";
    public final static String chessandcardSymbols = "♔♕♖♗♘♤♧♡♢";

    private static ArrayList<String> currentSymbols;
    private static final ArrayList<String> greek;
    private static final ArrayList<String> chessandcard;

    static {
        Log.i("LOG","Current Symbol created");
        currentSymbols = new ArrayList<>();

        greek = new ArrayList<>();
        createGreek();

        chessandcard = new ArrayList<>();
        createChessandcard();



        setCurrentGreek();
    }

    private static void createChessandcard(){
        for (int i=0;i<chessandcardSymbols.length();i++){
            chessandcard.add(chessandcardSymbols.charAt(i)+"");
        }
    }

    private static void createGreek(){
        for (int i=0;i<greekSymbols.length();i++){
            greek.add(greekSymbols.charAt(i)+"");
        }
    }
    public static void setCurrentGreek(){
        currentSymbols = greek;
    }

    public static void setCurrentChessandcard(){
        currentSymbols = chessandcard;
    }

    public static ArrayList<String> getCurrent(){
        return currentSymbols;
    }
}
