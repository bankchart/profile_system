import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login2Profile extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=utf8");
		PrintWriter out = res.getWriter();
		if(findMember(req, res)){

		}else{

		}
	}
	
	public boolean findMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBConnection conn = new DBConnection();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String sql = "SELECT username, password FROM member_tb WHERE " + 
			     "username = ? AND password = ? ";
		Object[] tmp = {"aaa", 2, 'c', 1.20, 25L};
		for(Object n : tmp){
			System.out.println(n.getClass());
		}	
		//conn.manageData(2, sql);	
		return false;
	}
}
