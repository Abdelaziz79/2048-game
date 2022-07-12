package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;



import java.util.*;

public class Main {

    public static void print(int arr[][],int n){
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr[0].length;j++){

                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void check(int arr[][],int flags[][]){
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                if(arr[i][j]>0)
                    flags[i][j]=1;
                else
                    flags[i][j]=0;
            }
        }
    }
    public static void random(int arr[][],int flags[][],int n){


        int r =(int) (Math.random()*n);
        int c = (int)(Math.random()*n);

        int count = 0;
        for(int i=0;i< arr.length;i++){
            for(int j=0;j< arr[0].length;j++){
                if(flags[i][j]==1)
                    count++;
            }
        }
        if(count==16)
            return;

        while (arr[r][c]>0){
            r = (int) (Math.random()*4);
            c = (int) (Math.random()*4);
        }

        int x = (int)(Math.random()*2);
        if(x==1)
            arr[r][c] = 2;
        else
            arr[r][c] = 4;
        check(arr,flags);
    }

    public static void upp(int arr[][],int flags[][],int i,int j){
        if(i==0) return;
        if(arr[i-1][j]==0||arr[i-1][j]==arr[i][j]){
            arr[i - 1][j] += arr[i][j];
            arr[i][j] = 0;
            check(arr,flags);
            upp(arr,flags,i-1,j);
        }else
            return;
    }
    public static void up(int arr[][],int flags[][]){
        for(int i=1;i< arr.length;i++){
            for(int j=0;j< arr[0].length;j++){
                upp(arr,flags,i,j);
            }
        }
    }

    public static void downn(int arr[][],int flags[][],int i,int j){
        if(i==3) return;
        if(arr[i+1][j]==0||arr[i+1][j]==arr[i][j]){
            arr[i + 1][j] += arr[i][j];
            arr[i][j] = 0;
            check(arr,flags);
            downn(arr,flags,i+1,j);
        }else
            return;
    }
    public static void down(int arr[][],int flags[][]){
        for(int i=2;i>=0;i--){
            for(int j=0;j< arr[0].length;j++){
                downn(arr,flags,i,j);
            }
        }
    }

    public static void rightt(int arr[][],int flags[][],int i,int j){
        if(j==3) return;
        if(arr[i][j+1]==0||arr[i][j+1]==arr[i][j]){
            arr[i][j+1] += arr[i][j];
            arr[i][j] = 0;
            check(arr,flags);
            rightt(arr,flags,i,j+1);
        }else
            return;
    }
    public static void right(int arr[][],int flags[][]){
        for(int i=0;i< arr.length;i++){
            for(int j=2;j>=0;j--){
                rightt(arr,flags,i,j);
            }
        }
    }

    public static void leftt(int arr[][],int flags[][],int i,int j){
        if(j==0) return;
        if(arr[i][j-1]==0||arr[i][j-1]==arr[i][j]){
            arr[i][j-1] += arr[i][j];
            arr[i][j] = 0;
            check(arr,flags);
            leftt(arr,flags,i,j-1);
        }else
            return;
    }
    public static void left(int arr[][],int flags[][]){
        for(int i=0;i< arr.length;i++){
            for(int j=1;j< arr[0].length;j++){
                leftt(arr,flags,i,j);
            }
        }
    }

    public static boolean gameOver(int arr[][],int flags[][]){
        int c = 0;
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(flags[i][j]==1)
                    c++;
            }
        }
        if(c == 16 ){
            for(int i=0;i< 4;i++){
                for(int j=0;j< 4;j++) {
                    int x = i + 1;
                    int y = j + 1;
                    if (y == 3 && x < 3) {
                        if (arr[i][j] == arr[x][j])
                            return false;
                    } else if (x == 3 && y<3) {
                        if (arr[i][j] == arr[i][y])
                            return false;
                    }
                    else if(x <3 && y<3) {
                        if (arr[i][j] == arr[i][y]||arr[i][j] == arr[x][j])
                            return false;
                    }
                }
            }
            return true;
        }
        else
            return false;
    }

    public static char autoPlay(int c){
        char arr[]={'w','d','s','a'};
        return arr[c%4];
    }

    public static void main(String[] args) {

        int arr[][] = new int[4][4];
        int flags[][] = new int[4][4];
        Scanner in = new Scanner(System.in);

//        random(arr,flags,4);

        int c = 0;

        while (!gameOver(arr,flags)){

            random(arr,flags,4);

            print(arr,4);
            //char s = in.next().charAt(0);
            char s = autoPlay(c);
            c++;
            if(s =='w') up(arr,flags);
            else if(s =='s') down(arr,flags);
            else if(s =='d') right(arr,flags);
            else if(s =='a') left(arr,flags);
            else System.out.println("m4 da");

        }

        System.out.println("after "+c+" moves");
        System.out.println("-------------GAME OVER-------------");


    }
}
