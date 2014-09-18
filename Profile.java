import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile extends HttpServlet {
	private Logger log = Logger.getLogger(Profile.class.getName());
	private HttpSession session;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mode = req.getParameter("mode");
		String view = req.getParameter("view");
		PrintWriter out = res.getWriter();
		session = req.getSession(true);
		if("login".equals(mode)){
			if(isLogin((String)session.getAttribute("user"))){
				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
				// query data and send to profile.jsp
				getAllData(req, res, (String)session.getAttribute("user"));
				rd.forward(req, res);
			}else{
				res.sendRedirect("index.html");
			}
		}else if("logout".equals(mode)){
			session.setAttribute("user", "");
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}else if("adminviewer".equals(mode)){
			if(existMember(session)){
				if(isAdmin(session)){
					String career = req.getParameter("career");
					if(null == career)
						career = "all";
					log.info("check career --> " + career);
					req.setAttribute("allMember", getAllMember(session, req, career));
					req.getRequestDispatcher("admin-viewer.jsp").forward(req, res);	
				}else{
					res.sendRedirect("Profile");
				}				
			}else{
				res.sendRedirect("Login2Profile");
			}	
				
		}else if(null != view){
			getAllData(req, res, view);		
			req.getRequestDispatcher("profile.jsp").forward(req, res);
		}else{
			if(isLogin((String)session.getAttribute("user"))){
				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");		
				// query data and send to profile.jsp
				getAllData(req, res, (String)session.getAttribute("user"));	
				rd.forward(req, res);
			}else{ 
				res.sendRedirect("Login2Profile");
			}
		}
	}
	// using this getAllMember method : exist admin session only.
	public String[][] getAllMember(HttpSession session, HttpServletRequest req, String career){
		DBConnection conn = new DBConnection();
		if("all".equals(career))
			career = "1 = 1";
		else if("manager".equals(career))
			career = "m.career_id = 1";
		else
			career = "m.career_id = 2";
		
		String fields = " username, fullname, birthdate, phone, email, career_name ";
		String sql = "SELECT "+ fields + " FROM member_tb m, career_tb c WHERE m.career_id = c.career_id AND " + career + 
				" AND username <> '" + (String)session.getAttribute("user") + "'";	
		log.info("sql career --> " + sql);
		ResultSet rs = conn.manageData(sql);
		int countRow = 0;
		try{
			while(rs.next())
				countRow++;	
			rs.beforeFirst();
			int i = 0;
			String[][] allMember = new String[countRow][6];
			req.setAttribute("rows", countRow);
			req.setAttribute("cols", 6);
			while(rs.next()){
				allMember[i][0] = rs.getString("username");
				allMember[i][1] = rs.getString("fullname");
				String birthTmp = "";
				if(null != rs.getString("birthdate")){
					String[] arr_Tmp = rs.getString("birthdate").split("-"); 
					birthTmp = arr_Tmp[2] + "/" + arr_Tmp[1] + "/" + arr_Tmp[0]; 
				}else{
					birthTmp = "-";
				}
				allMember[i][2] = birthTmp;		
				allMember[i][3] = (null == rs.getString("phone")) ? "-" : rs.getString("phone");
				allMember[i][4] = (null == rs.getString("email")) ? "-" : rs.getString("email");
				allMember[i][5] = rs.getString("career_name");
				i++;
			}
			return allMember;
		}catch(SQLException ex){
			log.info(sql + " --> " + ex.getMessage());
			return null;
		}
	}
	public boolean isAdmin(HttpSession session){
		DBConnection conn = new DBConnection();
		String sql = "SELECT * FROM member_tb WHERE admin = 1 AND username = '" + (String)session.getAttribute("user") + "'";
		ResultSet rs = conn.manageData(sql);
		int count = 0;
		try{
			while(rs.next()){
				count++;	
			}	
			log.info("admin-viewer -->> " + count);
			if(count != 0)
				return true;
			else
				return false;
		}catch(SQLException ex){
			log.info(sql + " -->" + ex.getMessage());
			return false;
		}
	}
	public boolean existMember(HttpSession session){
		if(null == session.getAttribute("user")){
			session.setAttribute("user", "");
			return false;
		}
		return true;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String[] careers = req.getParameterValues("career");
		DBConnection conn = new DBConnection();
		for(String n : careers){
			String[] tmp = n.split("-");
			if("nocareer".equals(tmp[1]))
				tmp[1] = "no-career";
			String sql = "UPDATE member_tb set career = '" + tmp[1] + "' WHERE username = '" + tmp[0] + "'";	
			if(!conn.executeData(sql))
				log.info("setup career failure.");
		}
		res.sendRedirect("Profile?mode=adminviewer");
	}
	public void getAllData(HttpServletRequest req, HttpServletResponse res, String username) throws ServletException, IOException {
                                DBConnection conn = new DBConnection();
                                //String username = (String)session.getAttribute("user");
                                String sql = "SELECT * FROM member_tb WHERE username = '" + username + "'";
                                ResultSet rs = conn.manageData(sql);
                                String password = "";
                                String fullname = "";
                                String birthdate = "";
                                String height = "";
                                String weight = "";
                                String blood_type = "";
                                String hobby = "";
                                String phone = "";
                                String email = "";
                                String detail_edu = "";
                                String detail_fav = "";
                                String detail_lsk = "";
                                String picture_path = "";
				String admin = "";
                                try{
                                while(rs.next()){
                                        password = rs.getString("password");
                                        fullname = rs.getString("fullname");
                                        birthdate = rs.getString("birthdate");
                                        height = rs.getString("height");
                                        weight = rs.getString("weight");
                                        blood_type = rs.getString("blood_type");
                                        hobby = rs.getString("hobby");
                                        phone = rs.getString("phone");
                                        email = rs.getString("email");
                                        detail_edu = rs.getString("detail_edu");
                                        detail_fav = rs.getString("detail_fav");
                                        detail_lsk = rs.getString("detail_lsk");
                                        picture_path = rs.getString("picture_path");
					admin = rs.getString("admin");
                                }
                                }catch(SQLException ex){
                                        log.info(ex.getMessage());
	
				}
                                req.setAttribute("username", username);
                                req.setAttribute("password", password);
                                req.setAttribute("fullname", fullname);
                                req.setAttribute("birthdate", birthdate);
                                req.setAttribute("height", height);
                                req.setAttribute("weight", weight);
                                req.setAttribute("blood_type", blood_type);
                                req.setAttribute("hobby", hobby);
                                req.setAttribute("phone", phone);
                                req.setAttribute("email", email);
                                req.setAttribute("detail_edu", detail_edu);
                                req.setAttribute("detail_fav", detail_fav);
                                req.setAttribute("detail_lsk", detail_lsk);
				req.setAttribute("admin", admin);

	}	
	public boolean isLogin(String username){
		DBConnection conn = new DBConnection();
		String sql = "SELECT username FROM member_tb WHERE username = '" + username + "'";
		ResultSet rs = conn.manageData(sql);
		int num = 0;
		try{
		while(rs.next())
			num++;
		}catch(SQLException ex){
			log.info(ex.getMessage());	
		}
		if(num == 0)
			return false;
		else 
			return true;
	}

}
