/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kgate.controller;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author user
 */
public class TestDate {

    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
//        System.out.println(date);

//        Date da = new Date(2018, 09, 12);
        Calendar rightNow = Calendar.getInstance();
        rightNow.getTime();
        Date today = Calendar.getInstance().getTime();
        System.out.println(rightNow.getTime());
        System.out.println("Current date:::  "+today);

    }

}
