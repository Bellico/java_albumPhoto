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

@WebFilter(filterName = "photoFilter", urlPatterns = {"/albums/*", "/photos/*"})
public class LireFilter extends FilterFunctions implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        init_MyFilter(req, res);

        RightBean right = null;
        if (urlParams.get(1) != null) {
            if (urlParams.get(0).equals("photos")) {
                if (urlParams.get(1).equals("mesPhotos")) {
                    right = (isConnect() != null) ? new RightBean(0, 0, true, false, false, false) : new RightBean(0, 0, false, false, false, false);
                } else {
                    right = Right_On_Album(isConnect(), getPhoto(Tools.toInteger(urlParams.get(1))));
                }
            } else if (urlParams.get(0).equals("albums")) {
                if (urlParams.get(1).equals("mesAlbums")) {
                    right = (isConnect() != null) ? new RightBean(0, 0, true, false, false, false) : new RightBean(0, 0, false, false, false, false);
                } else if (urlParams.get(1).equals("nouveau")) {
                    right = (isConnect() != null) ? new RightBean(0, 0, true, false, false, false) : new RightBean(0, 0, false, false, false, false);
                } else {
                    right = Right_On_Album(isConnect(), getAlbum(Tools.toInteger(urlParams.get(1))));
                }
            }
        } else {
            right = new RightBean(0, 0, true, false, false, false);
        }

        if (right == null) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            if (!right.isLire()) {
                sendError("Cette page ne vous est pas autorisée");
            }
            chain.doFilter(request, response);
        }
    }
}
