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
    private ArrayList<String> kategorije;
    private ArrayList<Integer> maxPoeni;
    private ArrayList<Integer> poeni;
    
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
        
        flg=true;
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
            if(suma!=100) System.out.println("Zbirno mora biti 100 poena, ponovni pokusaj");
        }while(suma!=100);
        System.out.println("---------------------------------------------------------------------");
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
