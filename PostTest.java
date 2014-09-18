
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostTest extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
		res.setContentType("text/html; charset=utf8");
		PrintWriter out = res.getWriter();
		String mode = req.getParameter("mode");		
		out.print(mode);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
