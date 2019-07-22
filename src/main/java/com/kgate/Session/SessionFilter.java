package com.kgate.Session;

import com.kgate.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.HttpSessionRequiredException;

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            String page = ((HttpServletRequest) request).getRequestURI();
            System.out.println("page is " + page);
   if (page.endsWith("/login") || page.endsWith("/authenticate") || page.endsWith("publicTest") || page.contains("background") || page.contains("setUpTenant") || page.contains("downloadUserSessionReportsForTest")) {
                chain.doFilter(request, response);
            } else if (page.contains("images") || page.contains("css") || page.contains("scripts") || page.contains("fonts") || page.contains("html") || page.contains("startTestSession")) {
                chain.doFilter(request, response);
            } else {
                Employee employee = (Employee) ((HttpServletRequest) request).getSession().getAttribute("employee");
                if (employee == null) {
                    ((HttpServletResponse) response).sendRedirect("login");
//                    throw new HttpSessionRequiredException("Expected session attribute '" + employee + "'");
                } else {
                    chain.doFilter(request, response);
                }

            }

        } catch (IOException | ServletException ex) {
            ((HttpServletResponse) response).sendRedirect("login");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            ((HttpServletRequest) request).getSession().setAttribute("employee", sStackTrace);

            ((HttpServletResponse) response).sendRedirect("login");
        }

    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
