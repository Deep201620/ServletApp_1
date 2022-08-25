package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utility.IConstant.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader(HEADER_CACHE_CONTROL, CACHE_CONTROL_VALUE);
        response.setHeader(HEADER_PRAGMA, PRAGMA_VALUE);

        HttpSession session = request.getSession(false);
        session.invalidate();
        try {
            RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
            rd.forward(request, response);
        } catch (ServletException | IOException exception) {
            System.out.println("Some error occurred");
        }
    }
}
