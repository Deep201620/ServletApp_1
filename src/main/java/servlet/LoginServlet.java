package servlet;

import bean.LoginBean;
import dao.LoginDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.RequestDispatcherUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static utility.IConstant.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginDao loginDao;
    private LoginBean loginBean;
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    @Override
    public void init() {
        loginDao = new LoginDao();
        loginBean = new LoginBean();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

//        LoginBean loginBean = new LoginBean();
        loginBean.setUserEmail(email);
        loginBean.setUserPassword(password);
        try {
            if (loginDao.login(loginBean)) {
                LOGGER.info(LOGIN_LOG);
                sessionManagement(request, email, password);
                RequestDispatcherUtility.dispatchRequest(request,response,WELCOME_PAGE);
            } else {
                RequestDispatcherUtility.dispatchRequest(request,response,ERROR_PAGE);
                LOGGER.info(INVALID_MESSAGE_LOG);
            }
        } catch (ServletException | IOException exception) {
          LOGGER.error(exception.getMessage());
        }
    }
    private void sessionManagement(HttpServletRequest httpServletRequest, String email, String password){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(EMAIL, email);
        session.setAttribute(PASSWORD, password);
    }
}
