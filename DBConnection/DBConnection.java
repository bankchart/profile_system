package DBConnection;
import org.apache.log4j.Logger;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	private Logger log = Logger.getLogger(DBConnection.class.getName());
	private String url;
	private String dbName;
	private String driver;
	private String username;
	private String password;
	
	private Connection conn;	
	private Statement stm;
	private ResultSet rs;	

	public DBConnection(){
		url = "jdbc:mysql://localhost:3306/";
                dbName = "myprofile";
                driver = "com.mysql.jdbc.Driver";
                username = "root";
                password = "crv1990";
		connectDB();
	}
	public DBConnection(String url, String dbName, String driver, String username, String password){
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.username = username;
		this.password = password;
		connectDB();
	}
	public void connectDB(){
                try {
                        Class.forName(driver);
                        conn = DriverManager.getConnection(url + dbName, username, password);
                        //System.out.println("connected.");
			stm = conn.createStatement();
                }catch(Exception ex) {
                        ex.printStackTrace();
			log.info(ex.getMessage());
                }
}
	public void closeDB(){
		try{
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
	}
	// protect query sql
	public void prepareSql(Object[] data, PreparedStatement tmp){
		try{
		for(int i=0;i<data.length;i++)
			if(data[i] instanceof String){
				tmp.setString(i + 1, (String)data[i]);	
			}else if(data[i] instanceof Integer){
				tmp.setInt(i + 1, (int)data[i]);	
			}else{
				tmp.setDouble(i + 1, (double)data[i]);	
			}		
		}catch(SQLException ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
	}
	// select only. 
	public ResultSet manageData(String sql){
		try{
			rs = stm.executeQuery(sql);	
		}catch(SQLException ex){
			rs = null;
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return rs;
	}
	public ResultSet manageData(String sql, Object[] data){
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			prepareSql(data, pstm);	
			rs = pstm.executeQuery();
			return rs;
		}catch(SQLException ex){
			rs = null;
			ex.printStackTrace();
			log.info(ex.getMessage());
			return rs;
		}	

	}
	// insert update delete
	public boolean executeData(String sql){
		try{
		        stm.execute(sql);	
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			log.info("SQL ERROR : >>>>>>> " + ex.getMessage());
			return false;
		}
	}
}
