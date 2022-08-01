/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

/**
 *
 * @author Андрей
 */
class Time {
    private int sec = 0, minutes = 0;
    String time;
    
    public Time() { }

    public Time(int sec, int minutes) {
        this.sec = sec;
        this.minutes = minutes;
        time = null;
    }

    public void run() {
        try {
            if(sec == 59) {
                minutes++;
                sec = 0;
            }
            else {
            sec++;
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String get_time() {
        time = "";
        int sec_tmp = sec, minutes_tmp = minutes;
        if(minutes <= 9) {
            time += "0";
        }
        time += Integer.toString(minutes_tmp) + ":";

        if (sec <= 9) {
            time += "0";
        }
        time += Integer.toString(sec_tmp);
        return this.time;
    }
}
