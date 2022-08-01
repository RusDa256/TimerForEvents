/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Андрей
 */
public class Record implements Serializable {
    int id = -1;
    String title;
    String time;
    
    public Record(){ }
    
    public Record(int _id, String _title, String _time) {
      this.id = _id;
      this.title = _title;
      this.time = _time;
    }
  
    public int getId(){
      return id;
    }
  
    public String getTitle(){
      return title;
    }
  
    public String getTime(){
      return time;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
  
  @Override
  public String toString() {
      
    return "[ " + id + ", " + title + ", " + time + "]\n";
  }
}
