/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eindexserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joxy
 */
public class EindexServer {

    private ServerSocket ssocket;
    private int port;
    //private ArrayList<ConnectedChatRoomClient> clients;

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
    
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int odluka = -1;
        Ssluzba ssluzba = new Ssluzba();
        
        try {
            while(Ssluzba.login());
        } catch (IOException e1) {
            e1.printStackTrace();
	}
        
        while(odluka!= 0){
            Ssluzba.ispisiOpcije();
            System.out.print("Opcija:");
            odluka = Ssluzba.proveriOpciju();
            switch (odluka) {
                case 0:	
                    System.out.println("Izlaz iz programa");	
                    break;
                case 1:	
                    ssluzba.dodStud();	
                    break;
                case 2:	
                    ssluzba.dodPredmet();	
                    break;
                case 3:	
                    ssluzba.dodPredmet();	
                    break;
                default:
                    System.out.println("Nepostojeca komanda");
                    break;
                
            }
        }
    } 
}
