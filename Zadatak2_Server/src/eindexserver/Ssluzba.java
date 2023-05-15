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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author joxy
 */
public class Ssluzba {
   
    
    public static ArrayList<Student> studenti = new ArrayList<Student>();
    public ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
    
    private static String sP = System.getProperty("file.separator");
    private static File f = new File("."+sP+"files"+sP+"users.txt"); 

    public static File getF() {
        return f;
    }

    public static String podaciOStudentu(String username){
        for (Student s : studenti){
            if(s.getUsername().equals(username)) return s.toString();
        }
        return "podaci o studentu nisu pronadjeni";
    }
    
    public static int login(String uName, String password) throws IOException{

        //ucitavanje users.txt

        //provera da li postoji fajl
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            System.out.println("-------------------------------------");
            String username = uName;
            while((data = in.readLine()) != null) {
                String [] tokens = data.split(":");
                if(tokens.length!=3){
                    System.out.println("Greska pri ocitavanju" + tokens);
                }
                if(tokens[0].equals(username)){
                    System.out.println();
                    String pass = password;
                    if(tokens[1].equals(pass))
                    {
                        if(tokens[2].equals("admin")) return 1;
                        else if(tokens[2].equals("student")) return 2;
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
        return 0;
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
    
    public static void dodStud(String ime, String prezime, String index, String jmbg, String username, String password) throws FileNotFoundException, UnsupportedEncodingException{
        boolean postojiStud=false;
        for(Student s : studenti){
            if (s.getBrIndexa().equals(index)){
                System.out.println("Student sa brojem indexa " + index + "vec postoji");
                postojiStud=true;
            }
        }
        if(!postojiStud){
            studenti.add(new Student(ime, prezime,index,jmbg,username,password));
        }
    }
    public void dodPredmet(){
        predmeti.add(new Predmet());
    }
    
}
