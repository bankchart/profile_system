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

public class Login2ProfileController extends HttpServlet {
	private String uname;	
	private HttpSession session;	
	private Logger log = Logger.getLogger(Login2ProfileController.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		session = req.getSession();
		if(null == session.getAttribute("user"))
			session.setAttribute("user", "");
			String tmp = (String)session.getAttribute("user").toString();
		if(tmp.length() == 0)	
			req.getRequestDispatcher("login.jsp").forward(req, res);
		else
			res.sendRedirect("ProfileController");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf8");
		String mode = req.getParameter("mode");
		PrintWriter out = res.getWriter();
		if(mode.equals("login")){
		//	out.print(findMember(req, res, "login"));
			if(!findMember(req, res, "login"))
				out.print("incorrect");
			else{
				String username = req.getParameter("username");
				HttpSession session = req.getSession();
				session.setAttribute("user", username);
				Member mem = new Member();
				//mem.sqlExecute("query", "username", username, "", "");
				out.print(mem.getFullName());
			}
		
				
		}else if(mode.equals("isRepeatUsername")){
			if(true){
			//if(!findMember(req, res, "checkRepeat")){
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
				out.print("register failer *** .");
			}
		//	String tmp = registerMember(req, res);
		//	out.print(tmp);
		}else{
			out.print("failure.");
		}
			
	}
	
	public boolean registerMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		Member member = new Member();

                String username = req.getParameter("username");           
                String password = req.getParameter("passwd");
                String repassword = req.getParameter("repasswd");
                String fullname = req.getParameter("fullname");
                String birthdate = req.getParameter("birthdate");
                String height = req.getParameter("height");
                String weight = req.getParameter("weight");             
                String bloodType = req.getParameter("blood-type");
                String hobby = req.getParameter("hobby");
                String education = req.getParameter("education");
                String faculty = req.getParameter("faculty");
                String major = req.getParameter("major");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");

		if(birthdate.length() > 0){	
			String[] tmpDate = birthdate.split("/");
			birthdate = "'" + tmpDate[2] + "-" + tmpDate[1] + "-" + tmpDate[0] + "'";
		}else{
			birthdate = "null";
		}
		try{
			Double.parseDouble(height);
		}catch(Exception ex){
			height = "0"; 
		}
		try{
			Double.parseDouble(weight);
		}catch(Exception ex){
			weight = "0";
		}
		if("choose".equals(bloodType))
			bloodType = null;
		if("".equals(hobby))
			hobby = null;
		if("".equals(phone))
			phone = null;
		if("".equals(email)) 
			email = null;
                member.setUserName(username);
                member.setPassword(password);
                member.setFullName(fullname);
                member.setBirthDate(birthdate);
                member.setHeight(Float.parseFloat(height));
                member.setWeight(Float.parseFloat(weight));
                member.setBloodType(bloodType);
                member.setHobby(hobby);
                member.setPhone(phone);
                member.setEmail(email);
                member.setDetailEducation("null");
                member.setDetailFavorite("null");
                member.setDetailLittleSkill("null");
                member.setPicturePath("null");
                member.setAdmin(0);
                member.setCareerID(3);
		log.info("in register.");
		//String tmp = member.sqlExecute("insert", "" ,"" ,"" ,"");
			
		if(member.sqlExecute("insert", "" ,"" ,"" ,""))
			return true;
		else 
			return false;
		
	//	return tmp;
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
		//return rows;
	}
}
