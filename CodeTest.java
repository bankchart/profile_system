import java.sql.ResultSet; 

public class CodeTest {
	public static void main(String[]args){
		DBConnection conn = new DBConnection();
		ResultSet rs;
		conn.connectDB();
		String sql = "SELECT * FROM member_tb WHERE username = ? AND password = ?";
		rs = conn.manageData(2, sql, new Object[]{"uname1", "pass1"});	
		try{
		while(rs.next())
			println(rs.getString("username") + " | " + rs.getString("password"));
		}catch(java.sql.SQLException ex){
			ex.printStackTrace();
		}
		conn.closeDB();
	}
	public static void println(String str){
		System.out.println(str);
	}
}
