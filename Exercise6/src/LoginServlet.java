import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getServletPath());
        if (request.getParameter("username").equals("long") && request.getParameter("password").equals("dat")){
            response.sendRedirect("loginSuccess.jsp");
        } else {
            request.setAttribute("errorMessage", "Wrong username & password");
            request.getRequestDispatcher("logInPage/index.jsp").forward(request, response);
        }
    }

}