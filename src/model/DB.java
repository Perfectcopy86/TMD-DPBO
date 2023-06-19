/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rasyid andriansyah
 */
public class DB {
    private String connAddress = "jdbc:mysql://localhost/tmd_dpbo?user=root&password=";
    private Statement stmt = null; 
    private Connection conn = null;
    private ResultSet rs = null; 
    
    public DB() throws Exception, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // membuat koneksi ke mysql dan basis data
            conn = DriverManager.getConnection(connAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException es) {
            throw es;
        }
    }
    
    public void createQuery(String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            // eksekusi query
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)) {
                // ambil hasil query
                rs = stmt.getResultSet();
            }
        } catch (SQLException e) {

            throw e;
        }
    }
    
    public void createUpdate(String Query)throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            // eksekusi query
            int res = stmt.executeUpdate(Query);
        } catch (SQLException e) {
            // eksekusi jika query gagal di eksekusi
            throw e;
        }
    }
    
    public ResultSet getResult() throws Exception {
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception e) {
            return Temp;
        }
    }
    
    public void closeResult() throws Exception, SQLException {
        if(rs != null) {
            try {
                rs.close();
            }
            catch(SQLException es){
                rs = null;
                throw es;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException es) {
                stmt = null;
                throw es;
            }
        }
    }
    
    public void closeConnection() throws Exception, SQLException {
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException es){
                conn = null;
            }
        }
    }
    
}
