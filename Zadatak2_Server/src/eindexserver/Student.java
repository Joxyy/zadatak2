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
    private String jmbg;
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
    
    
    public Student(String ime, String prezime, String brIndexa, String jmbg, String username, String password) throws FileNotFoundException, UnsupportedEncodingException{
        
        this.brIndexa=brIndexa;
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Unos podataka o novom studentu");
        
        this.ime=ime;
        this.prezime=prezime;
        this.jmbg=jmbg;;
        this.username=username;
        this.pass=password;   
        
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Ssluzba.getF(),true), "UTF8"));
        out.println(this.username + ":"+ this.pass + ":" + "student");
        out.flush();
        out.close();
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Student je uspesno dodat");
        System.out.println(this.toString());
        System.out.println("---------------------------------------------------------------------");
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

    public void setJmbg(String jmbg) {
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

    public String getJmbg() {
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
