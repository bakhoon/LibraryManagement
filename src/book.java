
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class book {
    private static Connection connection;
    private static ResultSet rs;
    private static PreparedStatement insertbook;
    private static PreparedStatement searchMember;
    private static ArrayList<ArrayList> books;
    private static ArrayList book;
    private static PreparedStatement listBook;
    
    public static void addBook(String bname, Integer quantity){
        try{
            connection = DBConnection.getConnection();
            for (int i = 0; i < quantity; i++){
                insertbook = connection.prepareStatement("INSERT INTO BOOK (bname) VALUES (?)");
                insertbook.setString(1,bname);
                insertbook.executeUpdate();
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void deleteBook(Integer bookid){
        try{
            connection = DBConnection.getConnection();
            insertbook = connection.prepareStatement("DELETE FROM BOOK WHERE BID = ?");
            insertbook.setInt(1,bookid);
            insertbook.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<ArrayList> listBook(){
        books = new ArrayList();
        ArrayList<ArrayList> tempbooks = new ArrayList<ArrayList>();
        try{
            connection = DBConnection.getConnection();
            listBook = connection.prepareStatement("SELECT DISTINCT K.bid, K.bname, W.username FROM Book K, Borrow W WHERE K.bid = W.bid ORDER BY K.bid");
            rs = listBook.executeQuery();
            while(rs.next()){
                book = new ArrayList(Arrays.asList(rs.getInt("bid"), rs.getString("bname"), rs.getString("username")));
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
}
