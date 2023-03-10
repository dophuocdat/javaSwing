package Login_Register.com.Helper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author datdo
 */
public class ConnectionDB {
    
    private Connection con;
    private static ConnectionDB intance;
    
    public ConnectionDB() {
    }
    
    public static ConnectionDB getIntance() {
        if (intance == null) {
            intance = new ConnectionDB();
        }
        return intance;
    }
    
    public void connection() throws SQLException {
        String server = "localhost";
        String port = "3308";
        String database = "carrentalsys";
        String userName = "root";
        String password = "123456";
        
        con = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/"
                + database + "?useUnicode=true&characterEncoding=UTF-8", userName, password);

    }
    
    public Connection getCon() {
        return con;
    }
    
    public void setCon(Connection con) {
        this.con = con;
    }
    
    public static PreparedStatement preparedStatement(String sql, Object... agrs) throws SQLException {
        PreparedStatement p = null;
        getIntance().connection();
        if (sql.startsWith("{")) {
            p = getIntance().getCon().prepareCall(sql);
            
        } else {
            p = getIntance().getCon().prepareStatement(sql);
        }
        for (int i = 0; i < agrs.length; i++) {
            p.setObject(i + 1, agrs[i]);
        }
        return p;
    }
    
    public static ResultSet Query(String sql, Object... agrs) {
        try {
            PreparedStatement p = preparedStatement(sql, agrs);
            try {
                
                return p.executeQuery();
                
            } finally {
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public static void executeUpdate(String sql, Object... args) throws SQLException {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
  
    
    public static void main(String[] args) {
        try {
            CallableStatement cstmt = null;
            String date1 = "2022-11-29";
            String date2 = "2022-11-30";
            String sql = "{CALL `ThongKeTongGia`(?,?,?)}";
            cstmt = (CallableStatement) preparedStatement(sql);
            cstmt.setString(1, date1);
            cstmt.setString(2, date2);
            cstmt.registerOutParameter(3, Types.FLOAT);
            cstmt.execute();
            float a = cstmt.getFloat(3);
            System.out.println(a);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
