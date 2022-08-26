package servlet;

import bean.RegistrationBean;
import dao.RegistrationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jetbrains.annotations.NotNull;
import utility.RequestDispatcherUtility;

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

    private static final Logger LOGGER = (Logger) LogManager.getLogger(RegistrationServlet.class);

    @Override
    public void init() {
        registrationDao = new RegistrationDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name, email, password;
        LocalDate bDate;
        name = request.getParameter(NAME);
        email = request.getParameter(EMAIL);
        bDate = LocalDate.parse(request.getParameter(BDATE));
        password = request.getParameter(PASSWORD);

        RegistrationBean registrationBean = getRegisterBean(name, email, password, bDate);
        try {
            if (registrationDao.registration(registrationBean)) {
                LOGGER.info(REGISTRATION_LOG);
                RequestDispatcherUtility.dispatchRequest(request, response, LOGIN_PAGE);
            }
        } catch (ServletException | IOException exception) {
           LOGGER.error(exception.getMessage());
        }
    }

    private @NotNull RegistrationBean getRegisterBean(String name, String email, String password, LocalDate bDate) {
        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setUserName(name);
        registrationBean.setEmailId(email);
        registrationBean.setbDate(bDate);
        registrationBean.setPassword(password);
        return registrationBean;
    }
}
