package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println(Jazyk.jeNezaporneCislo("1a2"));
        System.out.println(Jazyk.jeIdentifikator2("a"));
        System.out.println(Jazyk.jeCeleCislo2("05"));
        System.out.println(Jazyk.jeCeleCislo("-05"));
        Jazyk.nactiAutomat("Soubor.txt");
    }
}