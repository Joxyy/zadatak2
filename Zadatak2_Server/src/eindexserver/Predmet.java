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
    private ArrayList<Integer> maxPoeni= new ArrayList<Integer>();
    public ArrayList<Integer> poeni = new ArrayList<Integer>();
    private int Ocena;

    public int getOcena() {
        return Ocena;
    }

    public void setOcena(int Ocena) {
        this.Ocena = Ocena;
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
    
    
    
    Scanner sc = new Scanner(System.in);
    
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
        }
        System.out.println("Unos max poena: " + unos);
        System.out.println("---------------------------------------------------------------------");
        
    }
    public int unosOcene(){
        int suma=0;
        for(int i=0; i<this.kategorije.size();i++){
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Unos ocene za " + kategorije.get(i));
            
            int unos = 0;
            do{
                unos=ocitajCeoBroj();
            }while(unos<=this.maxPoeni.get(i));
            suma+=unos;
        }
        
        if (suma < 51) return 5;
        else if (suma>=51 && suma<=60) return 6;
        else if (suma>=61 && suma<=70) return 7;
        else if (suma>=71 && suma<=80) return 8;
        else if (suma>=81 && suma<=90) return 9;
        else if(suma>=91 && suma<=100) return 10;
        else return -1;
    }
    
    public int ocitajCeoBroj(){
        int ceoBroj = 0;
        boolean notRead = true;
        do {
            if (sc.hasNextInt()) {
                ceoBroj = sc.nextInt();
                notRead = false;
            } else {
                System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
            }
            sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
        } while (notRead);
        return ceoBroj;
    }
}
