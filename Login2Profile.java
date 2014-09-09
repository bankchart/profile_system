import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login2Profile extends HttpServlet {
	private String uname;	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=utf8");
		PrintWriter out = res.getWriter();
		if(findMember(req, res)){
			out.print(uname);
		}else{
			out.print("incorrect");
		}
	}
	
	public boolean findMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		ResultSet rs = null;
		boolean isMember = false;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String unameTmp = "";
		String passTmp = "";
		int rows = 0;
		String sql = "SELECT username, password FROM member_tb WHERE " + 
			     "username = ? AND password = ? ";
		try{
		rs = conn.manageData(2, sql, new Object[]{username, password});	
			while(rs.next()){
				uname = rs.getString("username");	
				rows++;
			}
			if(rows == 1)
				isMember = true;
				
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return isMember;
	}
}
