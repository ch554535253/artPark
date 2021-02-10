package com.artPark.util;

/**
 * @Author lbc on 2020/12/15  17:21.
 */
public class Father {
    int ii;
    static int fsi = 4;

    {
        System.out.println("4father init block");
    }

    static{
        System.out.println("5father static block");
    }

    static Son son = new Son();



    Father(){
        ii = 1;
        System.out.println("6father construct");
    }
}
