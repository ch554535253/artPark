package com.artPark.util;

/**
 * @Author lbc on 2020/12/15  17:46.
 */
public class Son extends Father{
    private int i = 1;
    private long l =2L;
    static int ssi =3;
    {
        System.out.println("1son init block");
    }

    static {
        System.out.println("2son static block");
    }

    Son(){
        l = 3L;
        System.out.println("3son construct");
    }
}
