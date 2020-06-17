/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class member {
    private static Connection connection;
    private static ResultSet rs;
    private static PreparedStatement insertMember;
    private static PreparedStatement searchMember;
    private static ArrayList<ArrayList> members;
    private static ArrayList<String> member;
    private static PreparedStatement listMember;
    private static ArrayList<ArrayList> temps;
    private static ArrayList<String> temp;
    private static PreparedStatement callMember;
    
    public static String registerMember(String uname, String fname, String lname){
        temp = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            callMember = connection.prepareStatement("SELECT username FROM MEMBER");
            rs = callMember.executeQuery();
            while(rs.next()){
                String thetemp = rs.getString("username");
                temp.add(thetemp);
            }
            if (temp.contains(uname)){
                return (uname + " is already added in the member.");
            }
            else{
                insertMember = connection.prepareStatement("INSERT INTO MEMBER (username, firstname, lastname) VALUES (?,?,?)");
                insertMember.setString(1,uname);
                insertMember.setString(2,fname);
                insertMember.setString(3,lname);
                insertMember.executeUpdate();
                return(uname + " is added to our member.");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static ArrayList<ArrayList> searchMember(String uname){
        members = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            searchMember = connection.prepareStatement("SELECT * FROM MEMBER WHERE username = ?");
            searchMember.setString(1,uname);
            rs = searchMember.executeQuery();
            System.out.println(searchMember);
            while(rs.next()){
                member = new ArrayList<String>(Arrays.asList(rs.getString("firstname"), rs.getString("lastname")));
                members.add(member);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return members;
    }
    public static ArrayList<ArrayList> listMember(){
        members = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            listMember = connection.prepareStatement("SELECT * FROM MEMBER");
            rs = listMember.executeQuery();
            System.out.println(listMember);
            while(rs.next()){
                member = new ArrayList<String>(Arrays.asList(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname")));
                if(members.contains(member)){
                    ;
                }
                else{
                    members.add(member);
                }
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return members;
    }
}
