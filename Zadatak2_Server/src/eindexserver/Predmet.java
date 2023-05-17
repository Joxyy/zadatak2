/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eindexserver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joxy
 */
public class Predmet {
    private String nazivPredmeta;
    public ArrayList<String> kategorije = new ArrayList<String>();
    public ArrayList<Integer> maxPoeni= new ArrayList<Integer>();
    public ArrayList<Integer> poeni = new ArrayList<Integer>();
    private int Ocena;

    public int getOcena() {
        return Ocena;
    }

    public void setOcena() {
        this.Ocena = unosOcene();
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public ArrayList<String> getKategorije() {
        return kategorije;
    }

    public ArrayList<Integer> getMaxPoeni() {
        return maxPoeni;
    }

    public ArrayList<Integer> getPoeni() {
        return poeni;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public void setKategorije(ArrayList<String> kategorije) {
        this.kategorije = kategorije;
    }

    public void setMaxPoeni(ArrayList<Integer> maxPoeni) {
        this.maxPoeni = maxPoeni;
    }

    public void setPoeni(ArrayList<Integer> poeni) {
        this.poeni = poeni;
    }
    
    

    
    public Predmet(String nazivPredmeta){
        System.out.println("---------------------------------------------------------------------");

        this.nazivPredmeta=nazivPredmeta;
        System.out.println("Unesen je predmet: " + this.nazivPredmeta);
        System.out.println("---------------------------------------------------------------------");
                    
        
    }
    public void unosKat(String unos){

        System.out.println("---------------------------------------------------------------------");
       
        String [] tokens = unos.split(" ");
        for(String t : tokens){
            kategorije.add(t);
        }
        System.out.println("Unos kategorija: " + unos);
    }
    
    public void unosMax(String unos){
       
        String [] tokens = unos.split(" ");
        for(String t : tokens){
            maxPoeni.add(Integer.parseInt(t));
            poeni.add(0);
        }
        System.out.println("Unos max poena: " + unos);
        System.out.println("---------------------------------------------------------------------");
        
    }

    public int unosOcene(){
        int suma=0;
        for(int i=0; i<this.kategorije.size();i++){
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Unos ocene za " + this.getNazivPredmeta());
            
            suma+=this.poeni.get(i);
        }
        
        if (suma < 51) return 5;
        else if (suma>=51 && suma<=60) return 6;
        else if (suma>=61 && suma<=70) return 7;
        else if (suma>=71 && suma<=80) return 8;
        else if (suma>=81 && suma<=90) return 9;
        else if(suma>=91 && suma<=100) return 10;
        else return -1;
    }
    
}
