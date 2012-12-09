package filter;

import command.ICommand;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "userFilter", urlPatterns = {"/albums/nouveau", "/upload/*"})
public class UserFilter extends FilterFunctions implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        //HashMap<Integer, String> urlParams = Tools.parseUrl(request.getRequestURI());

        if (isConnect(session) == null) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            chain.doFilter(request, response);
        }
    }
}
