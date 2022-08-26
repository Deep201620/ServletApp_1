package servlet;

import utility.RequestDispatcherUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utility.IConstant.LOGIN_PAGE;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        session.invalidate();
        try {
            RequestDispatcherUtility.dispatchRequest(request,response,LOGIN_PAGE);
        } catch (ServletException | IOException exception) {
            System.out.println("Logout failure due to: "+exception.getMessage());
        }
    }
}
