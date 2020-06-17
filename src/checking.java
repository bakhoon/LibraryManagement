/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class checking {
    public static boolean integerCheck(String getint){
        try{
            int i = Integer.parseInt(getint);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
}
