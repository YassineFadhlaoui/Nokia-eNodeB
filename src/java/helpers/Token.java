/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author yassine
 */
public class Token implements java.io.Serializable {

    private String Token;
    private long creationTime;

    public Token(String Token, long creationTime) {
        this.Token = Token;
        this.creationTime = creationTime;
    }

    public String getToken() {
        return Token;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Token{" + "Token=" + Token + ", creationTime=" + creationTime + '}';
    }

}
