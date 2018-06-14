package edu.hm.shareit.security;

import edu.hm.shareit.models.Token;
import edu.hm.shareit.models.User;

import java.util.HashMap;
import java.util.Map;

public class MySecurityManager {
    private static Map<Token, User> accessList = new HashMap<>();
    private static Map<Token, Double> expireList = new HashMap<>();

    private static String fail = "0";

    //Token is valid for 12 minutes
    private static final double EXPIRE_TIME = 12 * 60 * 1000;

    public static String getToken(User user){
        Token token = new Token();
        double time = System.currentTimeMillis();
        accessList.put(token, user);
        expireList.put(token, time);
        return token.getToken();
    }

    public static User getUser (Token token){
        return accessList.get(token);
    }

    public static boolean validateToken(Token token){
        boolean res = false;
        User user;
        double time = System.currentTimeMillis();
        if (accessList.containsKey(token)) {
            user = accessList.get(token);
            if (expireList.containsKey(token)) {
                double oldTime = expireList.get(token);
                if (Math.abs(time - oldTime) < EXPIRE_TIME) {
                    res = true;
                }
                else {
                    expireList.remove(token);
                    accessList.remove(token);
                    res = false;
                }
            }
            else {
                accessList.remove(token);
                res = false;
            }
        }
        else {
            res = false;
        }
        return res;
    }
}
