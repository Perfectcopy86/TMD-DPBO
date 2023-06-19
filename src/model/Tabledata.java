/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Rasyid andriansyah
 */
public class Tabledata extends DB{
    private String tableName; // nama tabel

    public Tabledata() throws Exception, SQLException{
        // konstruktor
        super();
        this.tableName = "player";
    }

    public void getTExperiences(){
        // mengeksekusi query untuk mengambil semua data pada tabel pengguna
        try {
            String query = "SELECT * from " + this.tableName;
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getDataTExperiences(String username) {
        // mengeksekusi query untuk mengambil 1 record data berdasarkan username
        try {
            String query = "SELECT * from " + this.tableName +" WHERE username='" + username + "'";
            createQuery(query);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void insertData(String username, int score, int standing){
        boolean update = false;
        try {
            Tabledata temp = new Tabledata();
            temp.getDataTExperiences(username);
            // cek apakah username sudah ada dalam database
            if(temp.getResult().next()) {
                update = true;
            } else {
                update = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // insert
        if(!update){
            try {
                String query = "INSERT INTO " + this.tableName + " VALUES(null, '" + username + "', " + Integer.toString(score) + ", " + Integer.toString(standing) + ")";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        // update
        else if(update){
            try {
                String query = "UPDATE " + this.tableName + " SET score=" + score + ", standing=" + standing + " WHERE username='" + username + "'";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Membuat datatable
    public DefaultTableModel setTable(){
        
        DefaultTableModel dataTable = null;
        try{
            // header tabel
            Object[] column = {"Username", "Score", "Standing"};
            dataTable = new DefaultTableModel(null, column);
            
            String query = "SELECT * from " + this.tableName + " order by score DESC";
            this.createQuery(query);
            while(this.getResult().next()){
                Object[] row = new Object[3];

                row[0] = this.getResult().getString(2);
                row[1] = this.getResult().getString(3);
                row[2] = this.getResult().getString(4);
                dataTable.addRow(row);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return dataTable;
    }
}
