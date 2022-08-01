/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Андрей
 */
public class ClientClass {
    
    Socket cs;
    
    DataInputStream dis;
    DataOutputStream dos;
    ObjectOutputStream to_client;
    Gson convert = new Gson();
    int id;
    ServerClass serverclass;

    InetAddress ip = null;
    DataBase db = new DataBase();
    
    Thread t;
    
    Record rec = new Record();
    String tmp = "";
    
    public ClientClass(int id, Socket _cs, ServerClass _serverclass) throws IOException {
        
        this.id = id;
        this.cs = _cs;
        this.serverclass = _serverclass;
        
        System.out.print("Подключитлся клиент " + id + "\n");
        
        dos = new DataOutputStream(cs.getOutputStream());
        
        t = new Thread (
            () -> {
                try{
                    dis = new DataInputStream(cs.getInputStream());
                    
                    while(true){
                        String obj;
                        obj = dis.readUTF();

                        Record rec = convert.fromJson(obj, Record.class);
                        System.out.print("Получил " + rec + "\n");
                        
                       
                        serverclass.addRec(rec);
                        
                        db.insertRecToDb(rec.id, rec.time, rec.title);
                    }   
                } catch (IOException ex) {
                    Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                serverclass.printAllRec();
            }
        );
        t.start();     
        sendID();
        
        Thread t1 = new Thread (
            () -> {
                try{  
                    while(true){
                        
                        for(MyPair pair : db.getAllPair()) {
                            
                            if(pair.get_time().equals(serverclass.timer.get_time())) {
                                rec.setId(-1);
                                rec.setTitle(pair.get_title());
                                
                                tmp = convert.toJson(rec);
                                dos.writeUTF(tmp);
                            }
                        }
                        rec.setId(-2);
                        rec.setTime(serverclass.timer.get_time());
                        Thread.sleep(1000);
                        
                        tmp = convert.toJson(rec);
                        dos.writeUTF(tmp);
                    }   
                } catch (IOException ex) {
                    Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
            }
                serverclass.printAllRec();
            }
        );
        t1.start(); 
    }
    
    void sendID(){
        Record rec = new Record();
        rec.setId(id);
        
        String sendStr = convert.toJson(rec);
        
        try {
            dos.writeUTF(sendStr);
            
            System.out.print("Отправил ID: " + rec + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void sendAllRec() {
        
        ArrayList<MyPair> allPair = new ArrayList<>();
        for( MyPair pair : db.getAllPair()) {
            allPair.add(pair);
        }
        try (ObjectOutputStream to_client = new ObjectOutputStream(cs.getOutputStream()))
        {
            to_client.writeObject(allPair);
            System.out.println("1");
        } catch (IOException ex) {
            Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void sendTimeFromClient() {
        try {
            dos.writeUTF(serverclass.timer.get_time());
            
            System.out.print("Отправил time\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
