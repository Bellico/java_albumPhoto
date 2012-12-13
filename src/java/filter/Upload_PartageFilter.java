package filter;

import bean.RightBean;
import command.ICommand;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import tools.Tools;

@WebFilter(filterName = "userFilter", urlPatterns = {"/upload/*", "/partage/*"})
public class Upload_PartageFilter extends FilterFunctions implements Filter {

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

        init_MyFilter(req, res);

        RightBean right = (isConnect() != null) ? new RightBean(0, 0, true, false, true, false) : new RightBean(0, 0, false, false, false, false);
        if (urlParams.get(0).equals("partage")) {
            if (urlParams.get(1) != null) {
                right = Right_On_Album(isConnect(), getAlbum(Tools.toInteger(urlParams.get(1))));
            }
        }

        if (right == null) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            if (!right.isModifier()) {
                sendError("Cette page ne vous est pas autorisée");
            }
            chain.doFilter(request, response);
        }
    }
}
