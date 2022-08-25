package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utility.IConstant.*;

@WebFilter("/*")
public class StopCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader(HEADER_PRAGMA, PRAGMA_VALUE);
        httpServletResponse.setHeader(HEADER_CACHE_CONTROL, CACHE_CONTROL_VALUE);
        httpServletResponse.setDateHeader(EXPIRE_DATE_HEADER,0);
        System.out.println("Filtered every request...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
