
package yazlab;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public  class mDatabase {
   private  Connection con ;
   private Statement statement ;   

   public mDatabase() {        
        createTable();
    }    
   public final  void connectDataBase() {         
          try {
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:server.db");
          } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.toString() );            
          }          
    }
   public final void createTable(){
        try {
            connectDataBase();
            statement = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS servers " +
                       "(capacity    INTEGER  NOT NULL," +
                       " id          INTEGER  NOT NULL, " + 
                       " request_ms  INTEGER  NOT NULL, " + 
                       " response_ms INTEGER  NOT NULL, " + 
                       " storage     INTEGER  )";
            statement.executeUpdate(query);           
            con.close();
            if(getServers().isEmpty()){
                addServer(10000,500,200,0);
                addServer(5000,500,300,0);
                addServer(5000,500,300,0);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }          
   }
   public void addServer(int capacity, int request_ms,int response_ms,int storage){
       int id;
       if(!getServers().isEmpty()){
            id=getServers().size();
       }else{
          id=0;
       }
        try {
            connectDataBase();
            statement = con.createStatement();
            String query = "INSERT INTO servers " +
                       "(capacity,id,request_ms,response_ms,storage)"+
                        "VALUES ("+capacity+","+id+","+request_ms+","+response_ms+","+storage+ ")";
            statement.executeUpdate(query);
            statement.close();       
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }          
   }
   public List<Map> getServers(){      
       List<Map> myServers = new ArrayList<>();     
       try {
            connectDataBase();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery( "SELECT * FROM servers;" );
            while (rs.next()) {               
               Map<String,Integer> myMap = new HashMap();
               myMap.put("capacity", rs.getInt("capacity"));
               myMap.put("request_ms", rs.getInt("request_ms"));
               myMap.put("response_ms", rs.getInt("response_ms"));
               myMap.put("storage", rs.getInt("storage"));
               myServers.add(myMap);              
           }
            con.close();
             return myServers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return myServers;
        }          
   }
   public int getStroage (int id){
       int value=0;
        try {
            connectDataBase();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery( "SELECT * FROM servers where id="+id +";" );
           value=rs.getInt("storage");               
            con.close();
             return value;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return value;
        }          
       
 
   }
   public void updateStroage(int storage,int id){
       try {
            connectDataBase();
            statement = con.createStatement();         
            String query = "UPDATE servers set storage ="+storage+" where id="+id+";";
            statement.executeUpdate(query);
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }    
   }
   public void deleteServer(int id){
       try {
            connectDataBase();
            statement = con.createStatement();  
            con.setAutoCommit(false);
            String query = "DELETE FROM servers where ID="+id+";";
            statement.executeUpdate(query);
            con.commit();
            
            int size=0;
            ResultSet rs = statement.executeQuery( "SELECT id FROM servers ORDER BY id;" );
            while (rs.next()) {               
                query = "UPDATE servers set id ="+size+" where ID="+rs.getInt("id")+ ";";
                size++;
                statement.executeUpdate(query);  
                con.commit();
           }            
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }    
   }     
}
