package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jazyk {
    static private boolean jeCislo(char znak){
        return Character.isDigit(znak);
    }
    static private boolean jePismeno(char znak){
        return Character.isLetter(znak);
    }
    static private boolean jePlusMinus(char znak){
        return (znak=='+')||(znak=='-');
    }
    static private char ctiZnak(String retezec, int pozice){
        return retezec.charAt(pozice);
    }
    static boolean jeNezaporneCislo(String text){
        for(int i=0;i<text.length();i++)
            if (!jeCislo(ctiZnak(text,i)))
                return false;
        return true;
    }
    static boolean jeIdentifikator(String text){
        if (!jePismeno(ctiZnak(text,0)))
            return false;
        for(int i=1;i<text.length();i++) {
            char znak = ctiZnak(text,i);
            if (!jeCislo(znak) && !jePismeno(znak))
                return false;
        }
        return true;
    }

    static boolean jeCeleCislo(String text){
        if(!jePlusMinus(text.charAt(0)) && !jeCislo(text.charAt(0))) return false;
        for(int i = 1; i < text.length(); i++){
            if(!jeCislo(text.charAt(i))) return false;
        }
        return jeCislo(text.charAt(text.length() - 1));
    }

    static boolean jeCeleCislo2(String text){
        char stav ='S';
        int pozice = 0;
        while(pozice<text.length()){
            char znak = ctiZnak(text,pozice);
            switch (stav){
                case 'S':
                    if (!jeCislo(znak) && !jePismeno(znak))
                        return false;
                    stav = 'A';
                    break;
                case 'A':
                    if (!jeCislo(znak))
                        return false;
                    break;
            }
            pozice++;
        }
        if (stav=='A') return true;
        return false;
    }

    static boolean jeIdentifikator2(String text){
        char stav ='S';
        int pozice = 0;
        while(pozice<text.length()){
            char znak = ctiZnak(text,pozice);
            switch (stav){
                case 'S':
                    if (!jePismeno(znak))
                        return false;
                        stav = 'A';
                    break;
                case 'A':
                    if (!jeCislo(znak) && !jePismeno(znak))
                        return false;
                    break;
            }
            pozice++;
        }
        if (stav=='A') return true;
        return false;
    }

    static Automat nactiAutomat(String soubor) throws FileNotFoundException {
        Automat automat = new Automat();
        try(BufferedReader reader = new BufferedReader(new FileReader("Soubor.txt"))){
            int pocetPravidel = Integer.parseInt(reader.readLine());
            automat.vychoziPravidlo = reader.readLine();
            for(int i = 0; i < pocetPravidel; i++){
                String radek = reader.readLine();
                String nazevPravidla = radek.substring(0, 1);
                String dalsiPravidlo = radek.substring(radek.length() - 1, radek.length());
                String pravidlo = radek.substring(1, radek.length() - 1).trim();
                automat.addPravidlo(nazevPravidla, new Pravidlo(nazevPravidla, pravidlo, dalsiPravidlo));
            }

        } catch (Exception ex){
            throw new RuntimeException("Chyba: " + ex.getMessage());
        }
        return automat;
    }

    private static class Automat {
        private String vychoziPravidlo;
        private HashMap<String, Pravidlo> pravidla = new HashMap<>();

        public String getVychoziPravidlo() {
            return vychoziPravidlo;
        }

        public void setVychoziPravidlo(String vychoziPravidlo) {
            this.vychoziPravidlo = vychoziPravidlo;
        }

        public HashMap<String, Pravidlo> getPravidla() {
            return pravidla;
        }

        public void setPravidla(HashMap<String, Pravidlo> pravidla) {
            this.pravidla = pravidla;
        }

        public void addPravidlo(String nazev, Pravidlo pravidlo){
            this.pravidla.put(nazev, pravidlo);
        }
    }

    private static class Pravidlo {
        String nazev;
        String popis;
        String dalsiPrechod;

        public Pravidlo(String nazev, String popis, String dalsiPrechod) {
            this.nazev = nazev;
            this.popis = popis;
            this.dalsiPrechod = dalsiPrechod;
        }
    }
}
