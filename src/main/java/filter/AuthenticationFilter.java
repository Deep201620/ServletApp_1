package filter;

import bean.LoginBean;
import dao.LoginDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebFilter("/login")
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LoginDao.class);

    public static boolean status = false;
    private PreparedStatement st = null;
    private PreparedStatement pstmt = null;
    private String mail, password;
    private int logincounter = 0;
    private LoginBean loginBean;
    static Connection conObj = DatabaseConnection.connect();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
