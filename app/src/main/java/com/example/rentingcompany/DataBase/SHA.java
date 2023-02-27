package com.example.rentingcompany.DataBase;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {


    public static String encryptSHA512(String text) {

        byte[] inputData1 = text.getBytes();
        byte[] outputData1 = new byte[0];

        try {
            outputData1 = SHA.encryptSHA(inputData1, "SHA-512");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger shaData1 = new BigInteger(1, outputData1);
        return shaData1.toString(16);
    }


    private static byte[] encryptSHA(byte[] data, String shaN) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(shaN);
        sha.update(data);
        return sha.digest();

    }
}
