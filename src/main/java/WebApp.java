import auth.Auth;
import auth.AuthService;
import filters.UserFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class WebApp {

    public static void main(String[] args) throws Exception {
        Auth authService = new AuthService();

        Server server = new Server(8081);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new CalcServlet()), "/calc/*");
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new LoginServlet(authService)), "/login/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout/*");
        handler.addServlet(new ServletHolder(new HistoryServlet()), "/history/*");
        handler.addServlet(new ServletHolder(new RedirectServlet("/register")), "/*");

        handler.addFilter(UserFilter.class,"/register/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
