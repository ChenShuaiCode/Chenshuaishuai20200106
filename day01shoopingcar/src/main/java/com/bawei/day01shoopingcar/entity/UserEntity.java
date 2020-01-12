package com.bawei.day01shoopingcar.entity;

/**
 * @author: asus
 * @date: 20/1/10
 */
public class UserEntity {
    public String message;
    public String status;
    public User result;


    public static class User{
        public String headPic;
        public String nickName;
        public String phone;
        public String sessionId;
        public String sex;
        public String userId;

    }
}
