package sqlConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Database {
	
	private static String hostName = "babicuni.database.windows.net";
    private static String dbName = "batnUserLog";
    private static String user = "babicuni";
    private static String password = "Vodavoda123";
    private static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
    private Connection connection = null;
    private static AdvancedEncryptionStandard aes = null;
    public Database() { 	
    	try {
    		connection = DriverManager.getConnection(url);
    		String schema = connection.getSchema();
    		System.out.println("Successful connection - Schema: " + schema);
    		aes = new AdvancedEncryptionStandard();
        	}
         catch (Exception e) {
             e.printStackTrace();
          }  	   	
     }
       
  /*  public ResultSet queryMe(String selectSql) {
    	 try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {
    			while (resultSet.next())
    	        {
    	            System.out.println(resultSet.getString(1) + " "
    	                + resultSet.getString(2));
    	        }
              return resultSet;
             }       	 
    	  catch (Exception e) {
              e.printStackTrace();
              return null;
           }  	    	 
    }
    */ 
    
    	public User queryLog(String user, String pass) {
    		try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM UserLogin WHERE CONVERT(VARCHAR, Username)='%s' AND CONVERT(VARCHAR, Password)='%s'", user, pass))) {
       			while (resultSet.next())
       	        {
       	           return new User(resultSet.getString(1),resultSet.getString(2));
       	        }
                 return null;
                }       	 
       	  catch (Exception e) {
                 e.printStackTrace();
                 return null;
              }  	
    	}
    	
    	public boolean queryCreateAcc(String user, String pass) {
    		
    		String aesUser = aes.cipherText(user);
    		String aesPass = aes.cipherText(pass);
    		try (Statement statement = connection.createStatement()) {	
    			return statement.execute(String.format("INSERT INTO UserLogin(Username,Password) VALUES('%s','%s')", aesUser, aesPass));
                }       	 
       	  catch (Exception e) {
                 e.printStackTrace();
                 return false;
              }  
    	}
    
    public void closeConnection() { 
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /* while (resultSet.next())
                    {
                        System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2));
                    }
     */
    
	
}
