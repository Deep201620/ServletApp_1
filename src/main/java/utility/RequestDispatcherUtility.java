package utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestDispatcherUtility {

    public static void dispatchRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                       String ResourceName) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(ResourceName);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

}
