/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

/**
 *
 * @author Андрей
 */
public class MyPair {
    private String time;
    private String title;
    
    public MyPair(String time, String title) {
        this.time = time;
        this.title = title;
    }
    
    public String get_time()   { return this.time; }
    public String get_title() { return this.title; }
    
}
