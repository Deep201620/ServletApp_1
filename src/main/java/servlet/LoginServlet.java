package servlet;

import bean.LoginBean;
import dao.LoginDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        LoginBean loginBean = new LoginBean();
        loginBean.setUserEmail(email);
        loginBean.setUserPassword(password);
        try {
            RequestDispatcher requestDispatcher;
            if (loginDao.login(loginBean)) {
                LOGGER.info(LOGIN_LOG);
                HttpSession session = request.getSession();
                session.setAttribute(EMAIL, email);
                session.setAttribute(PASSWORD, password);
                requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
                LOGGER.info(INVALID_MESSAGE_LOG);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
