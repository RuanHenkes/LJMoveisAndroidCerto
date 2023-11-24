package com.example.ljmoveisandroidcerto.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String hashPassword(String passaword) {

        try {
            //criar uma instancia
            MessageDigest md = MessageDigest.getInstance("SHA-256");


            //converta a senha em bytes
            byte[] passwordBytes = passaword.getBytes();


            //calcular o hash da senha
            byte[] hashedBytes = md.digest(passwordBytes);

            //converta o  hash em uma representação hetadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));

            }
            //retorne o hash como uma string em hexadecimal
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            //trate a exceção caso o algoritmo não esteja disponivel
            e.printStackTrace();
            return null;

        }
    }
}

