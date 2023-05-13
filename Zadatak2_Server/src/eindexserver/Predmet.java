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
    private ArrayList<String> kategorije = new ArrayList<String>();
    private ArrayList<Integer> maxPoeni= new ArrayList<Integer>();
    private ArrayList<Integer> poeni = new ArrayList<Integer>();
    
    Scanner sc = new Scanner(System.in);
    
    public Predmet(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Unos naziva novog predmeta");
        
        String unos = sc.nextLine();
        this.nazivPredmeta=unos;
                    
        this.unosKat();
        
    }
    public void unosKat(){
        boolean flg = true;
        do{
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Unos kategorije");
            String unos = sc.nextLine();
            kategorije.add(unos);
            
            System.out.println("Da li zelite da dodate jos kategorija (da/ne)");
            unos = sc.nextLine();
            if(unos.equalsIgnoreCase("ne")) flg=false;
        }while(flg);
        
        this.unosMax();
    }
    
    public void unosMax(){
        System.out.println("---------------------------------------------------------------------");
        int suma = 0;
        do{
            for (String k : kategorije){
                System.out.println("Unos max broja bodova za " + k +":");
                maxPoeni.add(this.ocitajCeoBroj());
            }
            for(int m : maxPoeni){
                suma+=m;
            }
            if(suma!=100) {
                System.out.println("Zbirno mora biti 100 poena, ponovni pokusaj");
                maxPoeni.clear();
                suma=0;
            }
        }while(suma!=100);
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
