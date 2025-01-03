package com.example.pcmspringbot1.model;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:12
@Last Modified Fri 20:12
Version 1.0
*/

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *  Login -
 *  private String username;
 *  private String password;
 *  Registrasi
 *  private String username;
 *     private String password;
 *     private String email;
 *     private String alamat;
 *     private String noHp;
 *  Form User -
 *   private Long id;
 *     private String username;
 *     private String password;
 *     private String email;
 *     private String alamat;
 *     private String noHp;
 *
 *  Forgot Password
 *  private String password;
 *     private String email;
 *
 * Change Password
 * private String password;
 *     private String email;
 */
public class User {

    @Id
    @GeneratedValue()
    private Long id;
    private String username;
    private String password;
    private String email;
    private String alamat;
    private String noHp;

    @Deprecated
    public void setId(@Deprecated Long id) {
        this.id = id;
    }
}