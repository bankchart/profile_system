import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
public class EditMember extends HttpServlet{
	
	private HttpSession session;
	private Logger log = Logger.getLogger(EditMember.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		session = req.getSession(true);
		String mode = req.getParameter("mode");
		if(null == (String)session.getAttribute("user")){
			session.setAttribute("user", "");
			res.sendRedirect("Profile");
		}else{
			String username = (String)session.getAttribute("user");
			DBConnection conn = new DBConnection();
			ResultSet rs = null;
			if("edit".equals(mode)){
				String fullname = req.getParameter("fullname").trim();
				String birthdate = req.getParameter("birthdate").trim();
				String height = req.getParameter("height").trim();
				String weight = req.getParameter("weight").trim();
				String blood_type = req.getParameter("blood_type");
				String hobby = req.getParameter("hobby").trim();
				String phone = req.getParameter("phone").trim();
				String email = req.getParameter("email").trim();
				String detail_edu = req.getParameter("detail_edu").trim();
				String detail_fav = req.getParameter("detail_fav").trim();
				String detail_lsk = req.getParameter("detail_lsk").trim();
					
				String[] tmp = birthdate.split("/");    
				if(tmp.length == 3){
                			birthdate = "'" + tmp[2] + "-" + tmp[1] + "-" + tmp[0] + "'";
				}else{
					birthdate = null;
				}
				height = ("-".equals(height)) ? "null" : height;
				weight = ("-".equals(weight)) ? "null" : weight;
				blood_type = ("-".equals(blood_type)) ? "null" : "'" + blood_type + "'";
				detail_edu = ("".equals(detail_edu)) ? "null" : "'" + detail_edu + "'";
				detail_fav = ("".equals(detail_fav)) ? "null" : "'" + detail_fav + "'";
				detail_lsk = ("".equals(detail_lsk)) ? "null" : "'" + detail_lsk + "'";
				String sql = "UPDATE member_tb SET " +
					"fullname = '" + fullname + "', " +
					"birthdate = " + birthdate + ", " +
					"height = " + height + ", " + 
					"weight = " + weight +  ", " +
					"blood_type = " + blood_type + ", " +
					"hobby = '" + hobby + "', " + 
					"phone = '" + phone + "', " +
					"email = '" + email + "', " +
					"detail_edu = " + detail_edu + ", " + 
					"detail_fav = " + detail_fav + ", " +
					"detail_lsk = " + detail_lsk  +  
					" WHERE username = '" + username + "'";
				if(conn.executeData(sql)){
					out.println("edit completed.");
					out.println("<meta http-equiv='refresh' content='1; url=Profile'/>");
				}else{
					out.println("edit failure.\n" + sql);
				}
			}
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("Profile");
	}

}
