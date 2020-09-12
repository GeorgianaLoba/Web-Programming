package webapp.servlet;

import webapp.model.User;
import webapp.model.validators.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = {"/servlet/register"})
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String againPassword = request.getParameter("againPassoword");
        String mail = request.getParameter("mail");

        boolean isValid = true;
        if (!password.equals(againPassword)){
            isValid=false;
        }
        User user = new User(username, password, mail);
        String errors = UserValidator.validate(user);
        if (errors.length()!=0){
            isValid=false;
        }
        RequestDispatcher requestDispatcher = null;
        if (isValid){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", username);
            requestDispatcher = request.getRequestDispatcher("/main.jsp");
        }
        else{
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("errors", errors);
            requestDispatcher = request.getRequestDispatcher("/failed.jsp");
        }
        requestDispatcher.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
