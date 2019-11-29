import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

public class WebApp {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8081);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new CalcServlet()), "/calc/*");
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new LoginServlet()),"/login/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()),"/logout/*");
        handler.addServlet(new ServletHolder(new HistoryServlet()), "/history/*");
        handler.addServlet(new ServletHolder(new RedirectServlet()), "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
