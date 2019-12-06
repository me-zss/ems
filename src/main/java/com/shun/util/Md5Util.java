package com.shun.util;


import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    private static MessageDigest md;
    private static BASE64Encoder en;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
            en = new BASE64Encoder();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toMD5(String password, String salt) {
        byte[] digest = null;
        try {
            digest = md.digest((password + salt).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return en.encode(digest);
    }

    public static Boolean verify(String password, String salt, String newPassword) {
        String s = toMD5(newPassword, salt);
        return password.equals(s);
    }
}
