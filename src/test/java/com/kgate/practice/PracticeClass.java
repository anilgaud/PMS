package com.kgate.practice;

import java.util.ArrayList;
import java.util.List;

public class PracticeClass {

    public static void main(String[] args) {
        System.out.println("com.kgate.practice.PracticeClass.main()");

        List<Object> list = new ArrayList<Object>();

        List<String> al = new ArrayList<String>();
        al.add("Amit");
        al.add("Vijay");
        al.add("Kumar");
        al.add(1, "Sachin");
        list.addAll(al);
        System.out.println("An element at 2nd position: " + al.get(2));
        System.out.println("Object::::::     " + list.get(0));
        for (String s : al) {
            System.out.println(s);
        }
    }
}
