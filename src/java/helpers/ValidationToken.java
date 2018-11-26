/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.security.SecureRandom;

/**
 *
 * @author yassine
 */
public class ValidationToken {
    public static final String DICT ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int TOKEN_LENGTH=40;
   public static String NewUserTokenGenerator(){
   SecureRandom random = new SecureRandom();
   StringBuilder sb = new StringBuilder(TOKEN_LENGTH);
   for( int i = 0; i < TOKEN_LENGTH; i++ ) 
      sb.append( DICT.charAt( random.nextInt(DICT.length()) ) );
   return sb.toString();
}
}
