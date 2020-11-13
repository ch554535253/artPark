package com.artPark.util;

/**
 * @Author lbc on 2020/10/27  10:37.
 */
public class QuickSort {
    static int count = 0;

    public static void qs(int[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int temp = arr[left];
        int l = left;
        int r = right;
        while (l<r){
            while(l<r && temp<=arr[r]){
                r--;
            }
            while(l<r && temp>=arr[l]){
                l++;
            }
            if(l<r){
                int a = arr[r];
                arr[r] = arr[l];
                arr[l] = a;
            }
        }
        arr[left] = arr[l];
        arr[l] = temp;
        count++;
        qs(arr,left,l-1);
        qs(arr,r+1,right);
    }

    public static void main(String[] args) {
//        int[] arr = {4,8,3,1,2,9,7,10,15,14,0,25};
//        int[] arr = {3,2,0,7,2,9,7,11,15,14,0,25};
//        int[] arr = {-8,0,-2,18,2,3,7,10,15,14,1,6};
        int[] arr = {7,12,11,10,9,8,1,2,3,4,5,6};
        qs(arr,0,arr.length-1);
        System.out.println();
        System.out.println("********");
        for (int a : arr){
            System.out.print(a+",");
        }
        System.out.println();
        System.out.println("count:"+count);
    }
}
