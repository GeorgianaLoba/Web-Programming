package webapp.servlet;

import webapp.model.User;
import webapp.utils.DatabaseConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "Login", urlPatterns = {"/servlet/login"})
public class Login extends HttpServlet {

    public User getUser(String username){
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from users where user_name= '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                return null;
            }
            User user = new User();
            user.setUsername(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("user_password"));
            user.setMail(resultSet.getString("user_mail"));
            resultSet.close();
            statement.close();
            connection.close();
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = this.getUser(username);
        boolean isValid = true;
        String errors = "";
        if (user == null ){
            errors = "Username not found!";
            isValid = false;
        }
        if (!user.getPassword().equals(password)){
            errors = "Incorrect password!";
            isValid = false;
        }
        RequestDispatcher requestDispatcher;
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
        String username = request.getParameter("username");
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", username);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
