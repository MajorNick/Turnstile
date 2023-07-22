package database;


import java.sql.*;


public class Database {
    private Connection con;
    public Database(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DatabaseInfo.server, DatabaseInfo.name,DatabaseInfo.password);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("can't connect to Database");
            System.exit(0);
        }
    }

    public Connection getConnection(){
        return con;
    }
    public void close(){
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
