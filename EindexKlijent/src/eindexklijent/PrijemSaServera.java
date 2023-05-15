/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eindexklijent;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joxyy
 */
public class PrijemSaServera implements Runnable{


    EindexKlijent parent;
    BufferedReader br;

    public PrijemSaServera(EindexKlijent parent) {
        //parent ce nam trebati da bismo mogli iz ovog thread-a da menjamo sadrzaj 
        //komponenti u osnovnom GUI prozoru (npr da popunjavamo Combo Box sa listom
        //korisnika
        this.parent = parent;
        //BufferedReader koristimo za prijem poruka od servera, posto su sve
        //poruke u formi Stringa i linija teksta, BufferedReader je zgodniji nego
        //da citamo poruke iz InputStream objekta
        this.br = parent.getBr();
    }
    
    @Override
    public void run() {
        //Beskonacna petlja
        while (true) {
            String line;
            try {

                line = this.br.readLine();

                if (line.equals("admin")) {
                    parent.pAdmin.setVisible(true);
                    parent.pLogin.setVisible(false);
                } else if(line.equals("student")){
                    parent.pAdmin.setVisible(true);
                    parent.pLogin.setVisible(false);
                }
                else if(line.equals("Neuspesan login")){
                    parent.lProveriLogin.setText("Pogresno uneti podaci");
                }
            } catch (IOException ex) {
                //Logger.getLogger(ReceiveMessageFromServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
