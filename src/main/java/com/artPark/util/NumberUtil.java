package com.artPark.util;

import java.util.*;

/**
 * @Author lbc on 2020/10/19  14:24.
 */
public class NumberUtil {
    public static String generateRandomByLen(int len){
        Random r = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String[] ss = new String[]{"20","10","60","10","10","700","5"};
//        Set<String> bb = new LinkedHashSet<String>();
//        for (int i = 0; i < ss.length; i++) {
//            bb.add(ss[i]);
//        }
//        Iterator<String> i = bb.iterator();
//        int index = 1;
//        while (i.hasNext()){
//            if(index ==3){
//                System.out.println("*************   "+i.next());
//                break;
//            }else {
//                index++;
//            }
//        }
    }
}
