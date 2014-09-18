import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
//import org.json.JSONObject;
//import org.json.JSONException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login2Profile extends HttpServlet {
	private String uname;	
	private Logger log = Logger.getLogger(Login2Profile.class.getName());
	private HttpSession session;	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//PrintWriter out = res.getWriter();
		session = req.getSession();
		if(null == session.getAttribute("user"))
			session.setAttribute("user", "");
			String tmp = (String)session.getAttribute("user").toString();
		if(tmp.length() == 0)	
			req.getRequestDispatcher("login.jsp").forward(req, res);
		else
			res.sendRedirect("Profile");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf8");
		String mode = req.getParameter("mode");
		PrintWriter out = res.getWriter();
		if(mode.equals("login")){
			if(!findMember(req, res, "login"))
				out.print("incorrect");
			else{
				String username = req.getParameter("username");
				HttpSession session = req.getSession();
				session.setAttribute("user", username);
				out.print(req.getParameter(username));
			}

				
		}else if(mode.equals("isRepeatUsername")){
			if(!findMember(req, res, "checkRepeat")){
				out.print("repeat");
			}else{
				out.print("no-repeat");
			}		
				
		}else if(mode.equals("register")){
			if(registerMember(req, res)){
				log.info("register completed.");
				out.print("<meta http-equiv='refresh' content='1; url=index.html'/>");
				out.print("register competed.");
			}else{
				log.info("register failure.");	
				out.print("register failer.");
			}
		}else{
			out.print("failure.");
		}
			
	}
	
	public boolean registerMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		String username = "'" + req.getParameter("username") + "'";		
		String password = "'" + req.getParameter("passwd") + "'";
		String repassword = req.getParameter("repasswd");
		String fullname = "'" + req.getParameter("fullname") + "'";
		String birthdate = req.getParameter("birthdate");
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");		
		String bloodType = req.getParameter("blood-type");
		String hobby = "'" + req.getParameter("hobby") + "'";
		String education = "'" + req.getParameter("education") + "'";
		String faculty = "'" + req.getParameter("faculty") + "'";
		String major = "'" + req.getParameter("major") + "'";
		String phone = "'" + req.getParameter("phone") + "'";
		String email = "'" + req.getParameter("email") + "'";
		if(birthdate.length() > 0){	
			String[] tmpDate = birthdate.split("/");
			birthdate = "'" + tmpDate[2] + "-" + tmpDate[1] + "-" + tmpDate[0] + "'";
		}else{
			birthdate = "null";
		}
		try{
			Double.parseDouble(height);
		}catch(Exception ex){
			height = "null";
		}
		try{
			Double.parseDouble(weight);
		}catch(Exception ex){
			weight = "null";
		}
		if(bloodType.equals("choose"))
			bloodType = "null";
		else
			bloodType = "'" + bloodType + "'";
		if(hobby.length() == 2)
			hobby = "null";	
		if(education.length() == 2)
			education = "null";
		if(faculty.length() == 2)
			faculty = "null";
		if(major.length() == 2)
			major = "null";
		if(phone.length() == 2)
			phone = "null";
		if(email.length() == 2)
			email = "null";	
		String sql = "INSERT INTO member_tb values " + 
                                " (null, " + 
                                 username + ", " +
                                 password + ", " +
                                fullname + ", " + 
                                 birthdate + ", " +
                                 height  + " , " +
                                weight + " , " + 
                                 bloodType + ", " +
                                 hobby + ", " +
                                 phone + ", " + 
                                  email + ",  " +
                                 "null ,  " +
                                "null ,  " + 
                                 "null ,  " +
				"null, " + 
				"0, " +
                                 "'no-career') ";
		log.info("register sql : >>>>>>>>> " + sql);
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
