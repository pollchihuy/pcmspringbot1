package com.example.pcmspringbot1.coretan;

import com.example.pcmspringbot1.utils.DataGenerator;

public class TestDataGenerator {

    public static void main(String[] args) {
        DataGenerator gen = new DataGenerator();
        System.out.println("Nama\t\tAlamat\t\t\tNo HP\t\temail");
        for (int i = 0; i < 10; i++) {
            System.out.println(gen.dataNamaLengkap()+"\t\t"+gen.dataAlamat()+"\t\t"+gen.dataNoHp()+"\t\t"+
                    gen.dataEmail()+"\t\t");
        }
    }
}
