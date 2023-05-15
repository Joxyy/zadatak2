/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eindexserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joxy
 */
public class EindexServer {

    private ServerSocket ssocket;
    private int port;
    private ArrayList<KonektovaniKlijenti> klijenti;
    
    public void setSsocket(ServerSocket ssocket) {
        this.ssocket = ssocket;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public ServerSocket getSsocket() {
        return ssocket;
    }
    public int getPort() {
        return port;
    }
    
    public EindexServer(int port) {
        this.klijenti = new ArrayList<>();
        try {
            this.port = port;
            this.ssocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(EindexServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void prihvatiKlijente() {
        Socket klijent = null;
        Thread thr;
        while (true) {
            try {
                System.out.println("Cekam klijente..");
                klijent = this.ssocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(EindexServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (klijent != null) {
                //Povezao se novi klijent, kreiraj objekat klase konektovani klijenti
                //koji ce biti zaduzen za komunikaciju sa njim
                KonektovaniKlijenti clnt = new KonektovaniKlijenti(klijent, klijenti);
                //i dodaj ga na listu povezanih klijenata jer ce ti trebati kasnije
                klijenti.add(clnt);
                //kreiraj novu nit (konstruktoru prosledi klasu koja implementira Runnable interfejs)
                thr = new Thread(clnt);
                //..i startuj ga
                thr.start();
            } else {
                break;
            }
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        Ssluzba ssluzba = new Ssluzba();
        EindexServer server = new EindexServer(6001);
        
        Scanner sc = new Scanner(System.in);
        int odluka = -1;
        
        
        System.out.println("Server pokrenut, slusam na portu 6001");

        //Prihvataj klijente u beskonacnoj petlji
        server.prihvatiKlijente();

    } 
}
