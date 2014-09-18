import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertTest {
	public static void main(String[]args){
		try{
		Connection conn;
		String url = "jdbc:mysql://localhost:3306/";
                String dbName = "myprofile";
                String driver = "com.mysql.jdbc.Driver";
                String uname = "root";
                String password1 = "crv1990";
                Class.forName(driver);
                conn = DriverManager.getConnection(url + dbName, uname, password1);
			
		//String sql = "insert into member_tb (username , password, fullname) values('uname5', 'pass5', 'fullname5')";
		String username = "uname6";
		String password = "pass6";
		String fullname = "fullname6";
		String birthdate = "2014-09-15";
		String height = "12.0";
		String weight = "125.05";
		String bloodType = "a";
		String hobby = "";
		String phone = "9865";
		String email = "xxxxx@gmail.com";
		String sql = "INSERT INTO member_tb values " + 
                                " (null, " + 
                                 " '" + username + "', " +
                                 " '" + password + "', " +
                                " '" + fullname + "', " + 
                                 " '" + birthdate + "', " +
                                 height  + " , " +
                                weight + " , " + 
                                 " '" + bloodType + "', " +
                                 " '" + hobby + "', " +
                                 phone + ", " + 
                                 " '" + email + "',  " +
                                 "null ,  " +
                                "null ,  " + 
                                 "null ,  " +
                                 "null) ";

		Statement stm = conn.createStatement();
		stm.execute(sql);
		System.out.println("insert completed.");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}


	}

}
