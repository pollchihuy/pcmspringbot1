package com.example.pcmspringbot1.praktikum;

import java.util.Scanner;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on 17/02/2025 19:57
@Last Modified 17/02/2025 19:57
Version 1.0
*/
public class Praktikum2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double douInput = 0.0;
        Double douTemp = 0.0;
        Integer intAvg = 0;
        Boolean isNext = true;

        /*
        12
        6
        8
        90
        161
         */
        while (isNext) {
            try{
                System.out.print("Masukkan Angka : ");
                douInput = sc.nextDouble();
                douTemp = douInput + douTemp;
                intAvg++;
            }catch (Exception e){
                isNext = false;
            }
        }
        System.out.println("Banyaknya Angka Yang Diinput adalah : "+intAvg+" Angka");
        System.out.println("Nilai Rata-Rata dari angka yang diinput adalah : "+(douTemp/intAvg));
    }
}
