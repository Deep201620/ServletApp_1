package servlet;

import bean.LoginBean;
import dao.LoginDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.IConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static utility.IConstant.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginDao loginDao;
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    @Override
    public void init(){
        loginDao = new LoginDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(HEADER_CACHE_CONTROL, CACHE_CONTROL_VALUE);
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader(HEADER_PRAGMA, PRAGMA_VALUE);

        String email = request.getParameter(IConstant.EMAIL);
        String password = request.getParameter(IConstant.PASSWORD);

        LoginBean loginBean = new LoginBean();
        loginBean.setUserEmail(email);
        loginBean.setUserPassword(password);
        try {
            RequestDispatcher requestDispatcher;
            if (loginDao.login(loginBean)) {
                LOGGER.info(IConstant.LOGIN_LOG);
                HttpSession session = request.getSession();
                session.setAttribute(IConstant.EMAIL, email);
                session.setAttribute(IConstant.PASSWORD, password);
                requestDispatcher = request.getRequestDispatcher(IConstant.WELCOME_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher(IConstant.ERROR_PAGE);
                requestDispatcher.forward(request, response);
                LOGGER.info(IConstant.INVALID_MESSAGE_LOG);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
