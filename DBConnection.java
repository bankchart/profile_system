import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

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
                }
	}
	public void closeDB(){
		try{
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	// protect query sql
	public void prepareSql(Object[] data, PreparedStatement tmp){
		try{
		for(int i=1;i<=data.length;i++)
			if(data[i-1] instanceof String){
				tmp.setString(i, (String)data[i-1]);	
			}else if(data[i-1] instanceof Integer){
				tmp.setInt(i, (int)data[i-1]);	
			}else{
				tmp.setDouble(i, (double)data[i-1]);	
			}		
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	// insert, delete, update
	public ResultSet manageData(String sql){
		try{
			rs = stm.executeQuery(sql);	
		}catch(SQLException ex){
			rs = null;
			ex.printStackTrace();
		}
		return rs;
	}
	public ResultSet manageData(int num, String sql, Object[] data){
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			prepareSql(data, pstm);	
			rs = pstm.executeQuery();
			return rs;
		}catch(SQLException ex){
			rs = null;
			ex.printStackTrace();
			return rs;
		}	

	}
	public boolean executeData(String sql){
		boolean isSuccess = false;
		try{
			isSuccess = stm.execute(sql);	
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return isSuccess;
	}
}
