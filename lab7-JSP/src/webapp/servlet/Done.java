package webapp.servlet;

import webapp.repository.Table;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Done", urlPatterns = {"/servlet/done"})
public class Done extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String status = request.getParameter("status");
        HttpSession httpSession = request.getSession();
        Table table = new Table(username);
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("status", status);
        httpSession.setAttribute("moves", table.getMoves().toString());
        table.initialise();
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/done.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
