/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conf;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Koneksi {
    private Connection c=null;
    private Statement st=null;
    private Config config;
    private String url;
    private String user;
    private String pass;
    
    public Koneksi() {
        config = new Config();
        url="jdbc:mysql://"+config.getHost()+"/"+config.getDatabase();
        user= config.getUsername();
        pass = config.getPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver MySQL tidak ditemukan!", "InfoBox: " + "Peringatan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean query (String q) {
        try {
            c= DriverManager.getConnection(url, user, pass);
            st= c.createStatement();
            st.executeUpdate(q);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Perintah database gagal!", "InfoBox: " + "KONEKSI ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ResultSet select (String q) {
        ResultSet rs=null;
        try {
            c= DriverManager.getConnection(url, user, pass);
            st = c.createStatement();
            rs= st.executeQuery(q);
        }catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Pengambilan (select) data dari database gagal!", "InfoBox: " + "KONEKSI ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    public Connection getKoneksi() {
        try {
            c= DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Koneksi gagal!", "InfoBox: " + "Peringatan", JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }
}
