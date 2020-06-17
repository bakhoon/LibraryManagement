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
/**
 *
 * @author HP
 */
public class borrow {
    private static Connection connection;
    private static ResultSet rs;
    private static PreparedStatement borrowing;
    private static PreparedStatement getborrow;
    private static PreparedStatement searchMember;
    private static ArrayList<ArrayList> books;
    private static ArrayList book;
    private static PreparedStatement listBook;
    private static PreparedStatement listbook;
    private static ArrayList<ArrayList> temps;
    private static ArrayList temp;
    private static PreparedStatement getBids;
    private static PreparedStatement getUsernames;
    private static ArrayList<Integer> BIDlist;
    private static ArrayList Userlist;
    private static PreparedStatement returnBook;
    
    public static String borrowBook(Integer bid, String uname){
            temps = new ArrayList();
            ArrayList temp2 = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            getborrow = connection.prepareStatement("SELECT * FROM BORROW");
            rs = getborrow.executeQuery();
            while(rs.next()){
                temp = new ArrayList(Arrays.asList(rs.getInt("bid"), rs.getString("username")));
                temps.add(temp);
            }
            temp2.add(bid);
            temp2.add(uname);
            if (temps.contains(temp2)){
                return (uname + " is already borrowed.");
            }
            else{
                borrowing = connection.prepareStatement("INSERT INTO BORROW (bid, username) VALUES (?,?)");
                borrowing.setInt(1,bid);
                borrowing.setString(2,uname);
                borrowing.executeUpdate();
                return(uname + " borrowed the book.");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<ArrayList> listBorrow(){
        books = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            listbook = connection.prepareStatement("SELECT * FROM BORROW ORDER BY bid");
            rs = listbook.executeQuery();
            while(rs.next()){
                Integer getint = rs.getInt("bid");
                String getstr = Integer.toString(getint);
                book = new ArrayList(Arrays.asList(rs.getString("bid"), rs.getString("username")));
                if(books.contains(book)){
                    ;
                }
                else{
                    books.add(book);
                }
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return books;
    }
    
    public static ArrayList getBid(){
        BIDlist = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            getBids = connection.prepareStatement("SELECT bid FROM BORROW");
            rs = getBids.executeQuery();
            while(rs.next()){
                Integer tempint = rs.getInt("bid");
                BIDlist.add(tempint);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return BIDlist;
    }
    
    public static ArrayList getUsername(){
        Userlist = new ArrayList();
        try{
            connection = DBConnection.getConnection();
            getUsernames = connection.prepareStatement("SELECT username FROM MEMBER");
            rs = getUsernames.executeQuery();
            while(rs.next()){
                String tempint = rs.getString("username");
                Userlist.add(tempint);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return Userlist;
    }
    
    public static String returnBook(Integer bookid){
        try{
            connection = DBConnection.getConnection();
            returnBook = connection.prepareStatement("DELETE FROM BORROW WHERE BID = ?");
            returnBook.setInt(1,bookid);
            returnBook.executeUpdate();
            return ("The BID #" + bookid + " is returned.");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
