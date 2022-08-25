package servlet;

import utility.IConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utility.IConstant.*;
import static utility.IConstant.PRAGMA_VALUE;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader(HEADER_CACHE_CONTROL, CACHE_CONTROL_VALUE);
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader(HEADER_PRAGMA, PRAGMA_VALUE);

        HttpSession session = request.getSession(false);
        session.invalidate();
        try {
            RequestDispatcher rd = request.getRequestDispatcher(IConstant.LOGIN_PAGE);
            rd.forward(request, response);
        } catch (ServletException | IOException exception) {
            System.out.println("Some error occured");
        }
    }
}
