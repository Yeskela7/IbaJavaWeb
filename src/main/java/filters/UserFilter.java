package filters;

import checking.Checking;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (Checking.checkLogin(request)) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
