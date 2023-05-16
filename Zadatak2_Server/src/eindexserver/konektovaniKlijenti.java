/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eindexserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joxyy
 */
class KonektovaniKlijenti implements Runnable{
        //atributi koji se koriste za komunikaciju sa klijentom
    private Socket socket;
    private String userName;
    private BufferedReader br;
    private PrintWriter pw;
    private ArrayList<KonektovaniKlijenti> sviKlijenti;
    
        //Konstruktor klase, prima kao argument socket kao vezu sa uspostavljenim klijentom
    public KonektovaniKlijenti(Socket socket, ArrayList<KonektovaniKlijenti> sviKlijenti) {
        this.socket = socket;
        this.sviKlijenti = sviKlijenti;
        

        //iz socket-a preuzmi InputStream i OutputStream
        try {
            //posto se salje tekst, napravi BufferedReader i PrintWriter
            //kojim ce se lakse primati/slati poruke (bolje nego da koristimo Input/Output stream
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            //zasad ne znamo user name povezanog klijenta
            this.userName = "";
        } catch (IOException ex) {
            Logger.getLogger(KonektovaniKlijenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
     * Metoda prolazi i pravi poruku sa trenutno povezanik korisnicima u formatu
     * Users: ImePrvog ImeDrugog ImeTreceg ... kada se napravi poruka tog
     * formata, ona se salje svim povezanim korisnicima
     */
    void studentiStatus() {
        //priprema string sa trenutno povezanim korisnicima u formatu 
        //Users: Milan Dusan Petar
        //i posalji svim konektovanim klijentima
        String studenti = "Studenti: ";
        
        for (Student s : Ssluzba.studenti) {
            studenti += " " + s.getBrIndexa();
        }
        for (KonektovaniKlijenti svimaUpdateCB : this.sviKlijenti) {
            svimaUpdateCB.pw.println(studenti);
        }

        
        System.out.println(studenti);
    }
    void klijentiStatus() {
        //priprema string sa trenutno povezanim korisnicima u formatu 
        //Users: Milan Dusan Petar
        //i posalji svim korisnicima koji se trenutno nalaze u chat room-u
        String connectedUsers = "Users:";
        for (KonektovaniKlijenti c : this.sviKlijenti) {
            connectedUsers += " " + c.getUserName();
        }


        System.out.println(connectedUsers);
    }
    void predmetiStatus() {
        String predmeti = "Predmeti: ";
            for (Predmet p : Ssluzba.predmeti){
            predmeti += " " + p.getNazivPredmeta();
        }
        for (KonektovaniKlijenti svimaUpdateCB : this.sviKlijenti) {
            svimaUpdateCB.pw.println(predmeti);
        }
        System.out.println(predmeti);
    }
    void predmetiStatus(String brIndexa) {
        String predmeti = "PredmetiKojeSlusaIndex: ";
            for (Student s : Ssluzba.studenti){
                if(s.getBrIndexa().equals(brIndexa)){
                for (Predmet p : s.predmetiKojeSlusa) predmeti += " " + p.getNazivPredmeta();
            }
        }
        for (KonektovaniKlijenti svimaUpdateCB : this.sviKlijenti) {
            svimaUpdateCB.pw.println(predmeti);
        }
        System.out.println(brIndexa+ " slusa: "+predmeti);
    }
    void katStatus(String predmet) {
        String kat = "KatPredmetaKojiSlusaIndex: ";
        for (Predmet p : Ssluzba.predmeti){
            if(p.getNazivPredmeta().equals(predmet)){
                for(String k : p.getKategorije())
                    kat += " " + k;
            }
        }
        for (KonektovaniKlijenti svimaUpdateCB : this.sviKlijenti) {
            svimaUpdateCB.pw.println(kat);
        }

    }
    
    @Override
    public void run() {
        //Server prima od svakog korisnika najpre njegovo korisnicko ime
        //a kasnije poruke koje on salje ostalim korisnicima u chat room-u
        while (true) {
            try {
                //ako nije poslato ime, najpre cekamo na njega
                if (this.userName.equals("")) {
                    String data = this.br.readLine();
                    String [] tokens = data.split(":");
                    if(Ssluzba.login(tokens[0], tokens[1])==0){
                        System.out.println("Neuspesan login");
                        this.userName=tokens[0];
                        this.pw.println("Neuspesan login");
                    }
                    else if(Ssluzba.login(tokens[0], tokens[1])==1){
                        System.out.println("Admin " + tokens[0] + " ulogovan");
                        this.userName=tokens[0];
                        this.pw.println("admin");
                    }
                    else if(Ssluzba.login(tokens[0], tokens[1])==2){
                        System.out.println("Student " + tokens[0] + " ulogovan");
                        this.userName=tokens[0];
                        this.pw.println("student");
                        this.pw.println(Ssluzba.podaciOStudentu(tokens[0]));
                    }
                    
                    
                    if (this.userName != null) {
                        System.out.println("Konektovani korisnici: " + this.userName);
                        //informisi sve povezane klijente da imamo novog 
                        //clana u chat room-u
                        klijentiStatus();
                    } else {
                        //ako je userName null to znaci da je terminiran klijent thread
                        System.out.println("Diskonektovani korisnici: " + this.userName);
                        for (KonektovaniKlijenti cl : this.sviKlijenti) {
                            if (cl.getUserName().equals(this.userName)) {
                                this.sviKlijenti.remove(cl);
                                break;
                            }
                        }
                        klijentiStatus();
                        break;
                    }
                    ////////CEKAMO KOMANDU/////////
                } else {
                    System.out.println("cekam poruku");
                    String line = this.br.readLine();
                    System.out.println(line);
                    System.out.println("stigla poruka");
                    if (line != null) {
                        String [] tokens = line.split(":");
                        if (tokens[0].equals("student")) {
                            Ssluzba.dodStud(tokens[1],tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                            studentiStatus();
                            predmetiStatus(tokens[3]);
                        }
                        else if (tokens[0].equals("noviAdmin")){
                            Ssluzba.dodAdmin(tokens[1],tokens[2]);
                        }
                        else if (tokens[0].equals("noviPredmet")){
                            Ssluzba.dodPredmet(tokens[1]);
                            predmetiStatus();
                        }
                        else if (tokens[0].equals("dodNaPredmet")){
                            Ssluzba.dodStudentaNaPredmet(tokens[1], tokens[2]);
                            //predmetiStatus();
                        }
                        else if (tokens[0].equals("novaKat")){
                            for(Predmet p : Ssluzba.predmeti){
                                if(p.getNazivPredmeta().equals(tokens[1])){
                                    p.unosKat(tokens[2]);
                                    p.unosMax(tokens[3]);
                                }
                            }
                        }
                        else if (tokens[0].equals("predmetiKojeSlusaIndex")){
                            predmetiStatus(tokens[1]);
                        }
                        else if (tokens[0].equals("KatPredmetaKojiSlusaIndex")){
                            katStatus(tokens[1]);
                        }
                        else if (tokens[0].equals("Ocena")){
                            Ssluzba.upisiOcenu(tokens[1], tokens[2], tokens[3], tokens[4]);
                        }

                    }

                }
            } catch (IOException ex) {
                System.out.println("Disconnected user: " + this.userName);

                Iterator<KonektovaniKlijenti> it = this.sviKlijenti.iterator();
                while (it.hasNext()) {
                    if (it.next().getUserName().equals(this.userName)) {
                        it.remove();
                    }
                }

            }

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUserName() {
        return userName;
    }

    public BufferedReader getBr() {
        return br;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public ArrayList<KonektovaniKlijenti> getSviKlijenti() {
        return sviKlijenti;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public void setSviKlijenti(ArrayList<KonektovaniKlijenti> sviKlijenti) {
        this.sviKlijenti = sviKlijenti;
    }
    
    
}
