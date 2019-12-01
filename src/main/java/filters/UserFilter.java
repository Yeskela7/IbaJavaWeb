package filters;

import checking.Checking;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (Checking.checkRegister(request)) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        } else {
            if (response instanceof HttpServletResponse) {
                HttpServletResponse rs = (HttpServletResponse) response;
                try {
                    rs.sendRedirect("register");
                    rs.getWriter().println("user is already registered");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
