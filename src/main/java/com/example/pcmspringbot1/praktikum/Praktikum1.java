package com.example.pcmspringbot1.praktikum;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on 17/02/2025 19:25
@Last Modified 17/02/2025 19:25
Version 1.0
*/

import java.util.Scanner;

/**
 * 1.	Buatlah sebuah program dengan menggunakan scanner. Jika inputan dibuat maka akan menghasilkan proses seperti berikut :
 * Percobaan pertama
 * (Input)		Masukkan Kata : Jquery 1995@v1.0
 * (Output)	Hasil	: 529
 *
 * Percobaan kedua
 * (Input) 		Masukkan Kata : Bebas Lepas@
 * (Output)	Hasil	: 238
 *
 * Berapakah output jika kata yang diinput adalah Tid4k $@ma
 */
public class Praktikum1 {

    public static void main(String[] args) {
 //        Jquery 1995@v1.0
        // 32 -47 adalah special character part 1 -> spasi ==> 32
        // 48 -57 adalah Numeric -> 1995 1 0
        // 58 -64 adalah Special Character Part 2 -> @
        // 65 -90 adalah Alfabet Huruf Besar -> J
        // 91 -96 adalah Special Character Part 3
        // 97 -122 adalah Alfabet Huruf Kecil -> queryv
        // 123 -126 adalah Special Character Part 4
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan Kata : ");
        String strInput = sc.nextLine();
        char [] chInput = strInput.toCharArray();
        int intTemp = 0;
        int intChar = 0;
        for (int i = 0; i < chInput.length; i++) {
            intChar = chInput[i];
            if(!(intChar >= 97 && intChar <= 122)) {
                intTemp = intTemp + intChar;
            }
        }
        System.out.println(strInput);
        System.out.println("Penambahan Ascii dari seluruh "+intTemp);
    }
}
