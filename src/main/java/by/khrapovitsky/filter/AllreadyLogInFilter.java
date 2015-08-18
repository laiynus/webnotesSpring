package by.khrapovitsky.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllreadyLogInFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("acptLogin") != null) {
            res.sendRedirect("lastnotes.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
