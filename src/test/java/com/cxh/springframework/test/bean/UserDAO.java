package com.cxh.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("00000", "--");
        hashMap.put("00001", "张三");
        hashMap.put("00002", "李四");
        hashMap.put("00003", "王五");
    }

    public String queryUserName(String uid){
        return hashMap.get(uid);
    }
}
