/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eindexserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author joxy
 */
public class Student {
    private String ime;
    private String prezime;
    private String brIndexa;
    private long jmbg;
    private Predmet predmet;
    
    private String username;
    private String pass;
    
    Scanner sc = new Scanner(System.in);
    
    String sP = System.getProperty("file.separator");
    File f = new File("."+sP+"files"+sP+"users.txt");
    
    @Override
    public String toString() {
            return "Student: " + this.ime + " " + this.prezime + "\nBroj indeksa: " + this.brIndexa + "\nJMBG: " + this.jmbg;
    }
    
    public Student(String brIndexa) throws FileNotFoundException, UnsupportedEncodingException{
        
        this.brIndexa=brIndexa;
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Unesite podatke o novom studentu");
        System.out.println("");
        
        System.out.println("Ime novog studenta:");
        String podatak = sc.nextLine();
        this.ime=podatak;
        
        System.out.println("Prezime novog studenta:");
        podatak = sc.nextLine();
        this.prezime=podatak;
        
        System.out.println("JMBG novog studenta:");
        this.jmbg=jmbgProvera();
        
        System.out.println("Postavite korisnicko ime:");
        podatak = sc.nextLine();
        this.username=podatak;
        
        System.out.println("Postavite lozinku:");
        podatak = sc.nextLine();
        this.pass=podatak;   
        
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Ssluzba.getF(),true), "UTF8"));
        out.println(this.username + ":"+ this.pass + ":" + "student");
        out.flush();
        out.close();
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Student je uspesno dodat");
        System.out.println(this.toString());
        System.out.println("---------------------------------------------------------------------");
    }
    
    
    public long jmbgProvera(){

        boolean notRead = true;
        do {
            String unos = sc.nextLine();
            String regexPattern = "^\\d{13}$";
            if(unos.matches(regexPattern)){
                String datum = unos.substring(0,2);
                int d = Integer.parseInt(datum);
                String mesec = unos.substring(2,4);
                int m = Integer.parseInt(mesec);
                String godina = unos.substring(4,7);
                int g = Integer.parseInt(godina);
                if(d<=31 && m<=12 && (g<5 || g>970)){
                    if(d==31){
                        regexPattern="^(?:0[13578]|1[012])$";
                        if(mesec.matches(regexPattern)) notRead=false;
                        else System.out.println("upisani mesec nema 31 dan");   
                    }else if(d==30){
                        if(mesec.equals("02")) System.out.println("upisani mesec nema 30 dana");
                        else notRead=false;
                    }else if(d==29 && mesec.equals("02")){
                        if(g%4==0) notRead=false; 
                        else System.out.println("Uneta godina nije prestupna");   
                    }else notRead = false;
                }
                if(!notRead) return Long.parseLong(unos);
            }else System.out.println("Neispravan unos JMBG-a");
        } while (notRead);
        
        return 0L;
    }
    

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBrIndexa(String brIndexa) {
        this.brIndexa = brIndexa;
    }

    public void setJmbg(long jmbg) {
        this.jmbg = jmbg;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrIndexa() {
        return brIndexa;
    }

    public long getJmbg() {
        return jmbg;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }
    
    
    
    
}
