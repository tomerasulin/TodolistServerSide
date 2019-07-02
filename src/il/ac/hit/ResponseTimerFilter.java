package il.ac.hit;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ResponseTimerFilter implements Filter {
    protected FilterConfig filterConfig;
    static Logger logger = Logger.getLogger(ResponseTimerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long elapsed = System.currentTimeMillis() - startTime;
        String name = "servlet";
        if(servletRequest instanceof HttpServletRequest){
            name = ((HttpServletRequest)servletRequest).getRequestURI();
        }
        logger.debug(name+" took "+elapsed+" ms");
    }

    @Override
    public void destroy() {

    }
}
