import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
//import org.json.JSONObject;
//import org.json.JSONException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login2Profile extends HttpServlet {
	private String uname;	
	private Logger log = Logger.getLogger(Login2Profile.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf8");
		String mode = req.getParameter("mode");
		PrintWriter out = res.getWriter();
		if(mode.equals("login")){
			if(!findMember(req, res, "login"))
				out.print("incorrect");
		
		}else if(mode.equals("isRepeatUsername")){
			if(!findMember(req, res, "checkRepeat")){
				out.print("repeat");
			}else{
				out.print("no-repeat");
			}		
				
		}else if(mode.equals("register")){
			if(registerMember(req, res)){
				log.info("register completed.");
			}else{
				log.info("register failure.");	
			}
		}else{
			out.print("failure.");
		}
			
	}
	
	public boolean registerMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		String username = req.getParameter("username");		
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		String fullname = req.getParameter("fullname");
		String birthdate = req.getParameter("birthdate");
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");		
		String bloodType = req.getParameter("blood-type");
		String age = req.getParameter("age");
		String hobby = req.getParameter("hobby");
		String education = req.getParameter("education");
		String faculty = req.getParameter("faculty");
		String major = req.getParameter("major");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		
		String[] tmpDate = birthdate.split("/");
		birthdate = tmpDate[2] + "-" + tmpDate[1] + "-" + tmpDate[0];

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

		if(conn.executeData(sql)){
			conn.closeDB();
			return true;
		}else{
			conn.closeDB();
			return false;
		}
	}

	public boolean findMember(HttpServletRequest req, HttpServletResponse res, String mode) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		ResultSet rs = null;
		boolean isMember = false;
		int rows = 0;
		try{
			if(mode.equals("login")){
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String sql = "SELECT username, password FROM member_tb WHERE " + 
			     	"username = ? AND password = ? ";
				rs = conn.manageData(sql, new Object[]{username, password});	
			}else if(mode.equals("checkRepeat")){
				String username= req.getParameter("memReg");
				String sql = "SELECT username From member_tb WHERE username = ?";
				rs = conn.manageData(sql, new Object[]{username});
			}
			while(rs.next()){
				//uname = rs.getString("username");	
				rows++;
			}
			if(rows == 1 && mode.equals("login"))
				isMember = true;
			else if(rows == 0 && mode.equals("checkRepeat"))
				isMember = true;
			conn.closeDB();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return isMember;
	}
}
