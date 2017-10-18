package com.alimusic.jettyAsync.main;
import com.alimusic.jettyAsync.server.SubjectServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * date 17/10/18 14:27
 *
 * @author: shuhan.lyn@alibaba-inc.com
 */
public class JettyHttpAsync {


    private static final Logger logger = LoggerFactory.getLogger(JettyHttpAsync.class);

    private static final int PORT = 9290;


    public static void main(String[] args) throws Exception {

        new JettyHttpAsync().startJetty(PORT);
    }

    private void startJetty(int port) throws Exception {
        logger.info("Starting server at port {}", port);
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        server.setHandler(context);

        context.addServlet(new ServletHolder(new SubjectServlet()), "/asyncSubject");


        server.start();

        logger.info("Server started at port {}", port);

        server.join();

    }

}
