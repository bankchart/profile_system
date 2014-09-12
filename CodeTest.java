import java.sql.ResultSet; 
import java.sql.Connection;
import java.sql.Statement;

public class CodeTest {
	public static void main(String[]args){
		DBConnection conn = new DBConnection();
		ResultSet rs;
		conn.connectDB();
		String sql = "INSERT INTO member_tb (userid, username, password, fullname) values (null, \"uname4\", \"pass4\", \"sompong\");";
		conn.closeDB();
	}
	public static void println(String str){
		System.out.println(str);
	}
}
