package edu.hm.shareit.models;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Token {

    private static final int RANDOM_BITS = 130;
    private static final int STRING_BITS = 32;

    private String token;


    /**
     * Constructor that generates a random and secure stable token.
     */
    public Token() {
        SecureRandom random = new SecureRandom();
        this.token = new BigInteger(RANDOM_BITS, random).toString(STRING_BITS);
    }

    /**
     * Getter for the token.
     *
     * @return token as string
     */
    public String getToken() {
        return this.token;
    }

    public void setToken(String token){this.token = token;}

    @Override
    public boolean equals(Object obj) {

        Token token = null;
        try {
            token = (Token) obj;
        } catch (ClassCastException ex) {
            return false;
        }
        return token.getToken().equals(this.getToken());
    }

    @Override
    public String toString() {
        return this.getToken();
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}