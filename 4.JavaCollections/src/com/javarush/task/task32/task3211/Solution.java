package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();

        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest mdA = MessageDigest.getInstance("MD5");
        mdA.update(byteArrayOutputStream.toByteArray());
        byte[] digest = mdA.digest();

        byte[] bytes = new byte[16];
        for(int i = 0; i < 16; i++) {
            bytes[i] = (byte)(Integer.parseInt(md5.substring(i*2, i*2+2), 16));

        }
        boolean result = mdA.isEqual(digest, bytes);
        return result;
    }
}
