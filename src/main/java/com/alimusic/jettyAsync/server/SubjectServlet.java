package com.alimusic.jettyAsync.server;

import com.alimusic.jettyAsync.controller.AsyncShell;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * date 17/10/18 15:05
 *
 * @author: shuhan.lyn@alibaba-inc.com
 */
public class SubjectServlet extends HttpServlet {


    private String msg = "run subject interpose!";

    public SubjectServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        AsyncShell.runShellCmd();
        response.getWriter().println("<h1>" + msg + "</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }

}
