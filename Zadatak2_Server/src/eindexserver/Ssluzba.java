/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eindexserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author joxy
 */
public class Ssluzba {
    
    
    
    public static boolean login() throws IOException{

        //	ucitavanje studenata
        //sada moze biti razliciti broj ocena
        //ucitavamo iz studenti.csv
        String sP = System.getProperty("file.separator");
        Scanner sc = new Scanner(System.in);
        
        File f = new File("."+sP+"files"+sP+"users.txt"); 
        //provera da li postoji fajl
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            System.out.println("-------------------------------------");
            System.out.println("Unesite korisnicko ime");
            String username = sc.nextLine();
            while((data = in.readLine()) != null) {
                String [] tokens = data.split(":");
                if(tokens.length!=3){
                    System.out.println("Greska pri ocitavanju" + tokens);
                    System.exit(0);
                }
                if(tokens[0].equals(username)){
                    System.out.println();
                    System.out.println("Unesite lozinku");
                    String pass = sc.nextLine();
                    if(tokens[1].equals(pass))
                    {
                        if(tokens[2].equals("admin")){
                            return false;
                        }else System.out.println("nemate administratorska prava");
                    }else System.out.println("pogresna lozinka");
                }
                else System.out.println("nepostojece korisnicko ime");
            }
            System.out.println("Neuspesno logovanje. Pokusajte ponovo");
            System.out.println("-------------------------------------");
            in.close();
        }catch(FileNotFoundException e) {
            System.out.println("Nije pronadjen users.txt!");
            System.exit(127);
        }
        return true;
    }
    
    public static void ispisiOpcije(){	
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\tOpcija broj 1 - Dodavanje/izmena podataka o studentu");
            System.out.println("\tOpcija broj 2 - Dodavanje novog predmeta");
            System.out.println("\tOpcija broj 3 - Dodavanje studenata na predmet");
            System.out.println("\tOpcija broj 4 - Dodela/azuriranje poena");
            System.out.println("\tOpcija broj 5 - Dodavanje admina");
            System.out.println("\t\t ...");
            System.out.println("\tOpcija broj 0 - IZLAZ");	
            System.out.println("-------------------------------------------------------------------------");
    }
    
    public static int proveriOpciju(){
        int opcija = -1;
        Scanner sc = new Scanner(System.in);
        
        boolean notRead = true;
        do {
                if (sc.hasNextInt()) {
                        opcija= sc.nextInt();
                        if(opcija>=0 && opcija<= 5) notRead = false;
                        else System.out.println("Nepostojeca opcija, pokusajte ponovo:");
                } else {
                        System.out.println("Ne odgovarajuc unos. Unesite broj ispred opcije:");
                }
                sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
        } while (notRead);
        
        return opcija;
    }
    
    public static void dodStud(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("-------------------------------------");
        System.out.println("Unesite broj indexa");
        String index = sc.nextLine();
    }
}