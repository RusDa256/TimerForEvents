/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Андрей
 */
public class ServerClass {
    
    int port = 3121;
    InetAddress ip;  
    int count = 0;
    ServerSocket ss;
    Time timer;
    DataBase db = new DataBase();
    Gson convert = new Gson();
    
    ArrayList<Record> allRec = new ArrayList<>();
    ArrayList<ClientClass> allClient = new ArrayList<>();
    
    DataOutputStream dos;
    
    public ServerClass() {
        
        timer = new Time();       
        Thread t = new Thread(
                () -> {
                    while(true)
                    {
                        timer.run();
                    }      
                }
        );
        t.start();

        try {
            ip = InetAddress.getLocalHost();
            
            ServerSocket ss = new ServerSocket(port, 0, ip);
            System.out.println("Сервер запущен");
            
            while(true) {
                Socket cs = ss.accept();
                count++;
                ClientClass sc = new ClientClass(count, cs, this);
                allClient.add(sc);
            }   
        } catch (IOException ex) {
            System.out.print("Не могу создать сервер");
        }       
    }
    
    public void addRec(Record rec) {
        allRec.add(rec);
    }
    
    public void printAllRec() {
        for(Record rec : this.allRec) {
            System.out.print(rec + "\n");
        }
    }
            
    public static void main(String[] args) {
        new ServerClass();
    }
}
