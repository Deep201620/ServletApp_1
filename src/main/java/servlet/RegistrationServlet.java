package servlet;

import bean.RegistrationBean;
import dao.RegistrationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static utility.IConstant.*;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private RegistrationDao registrationDao;
    private String name, email, password;
    LocalDate bDate;
    private static final Logger LOGGER = (Logger) LogManager.getLogger(RegistrationServlet.class);

    @Override
    public void init() {
        registrationDao = new RegistrationDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        name = request.getParameter(NAME);
        email = request.getParameter(EMAIL);
        bDate = LocalDate.parse(request.getParameter(BDATE));
        password = request.getParameter(PASSWORD);

        RegistrationBean registrationBean = getRegisterBean();
        try {
            if (registrationDao.registration(registrationBean)) {
                LOGGER.info(REGISTRATION_LOG);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private RegistrationBean getRegisterBean() {
        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setUserName(name);
        registrationBean.setEmailId(email);
        registrationBean.setbDate(bDate);
        registrationBean.setPassword(password);
        return registrationBean;
    }
}
