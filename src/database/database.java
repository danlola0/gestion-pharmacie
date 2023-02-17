
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class database {
       Connection con = null;
       String host="localhost";
       String dbname="gestion_pharmacie";
       String user="root";
       String pass="";
    public Connection getcon (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+dbname,user,pass);
       con = DriverManager.getConnection("jdbc:mysql://localhost/pharmacie","root","");
//
            System.out.println("connection reusssi");
        }catch(ClassNotFoundException | SQLException e){
        
         System.out.println("connection echouee "+e.getMessage());
        }
        return con;
    }
}
