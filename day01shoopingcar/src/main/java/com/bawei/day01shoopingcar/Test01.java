package com.bawei.day01shoopingcar;

import com.blankj.utilcode.util.EncryptUtils;

import java.security.spec.EncodedKeySpec;

/**
 * @author: asus
 * @date: 20/1/8
 */
public class Test01 {
    public static void main(String[] args) {
         String pwd="03AE77E704A0B8E35DD56077129CDFB2";
         System.out.println("加密前"+pwd);
         System.out.println("加密后"+ EncryptUtils.encryptMD5ToString(pwd));
    }
}
