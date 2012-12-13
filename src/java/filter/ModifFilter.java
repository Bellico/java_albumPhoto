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

@WebFilter(filterName = "modificationFilter", urlPatterns = {"/modif/*"})
public class ModifFilter extends FilterFunctions implements Filter {

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

        RightBean right = null;
        if (urlParams.get(1).equals("photo")) {
            right = Right_On_Album(isConnect(), getPhoto(Tools.toInteger(urlParams.get(2))));
        } else if (urlParams.get(1).equals("album")) {
            right = Right_On_Album(isConnect(), getAlbum(Tools.toInteger(urlParams.get(2))));
        } else if (urlParams.get(1).equals("utilisateur")) {
            right = Right_On_User(isConnect(), getUser(Tools.toInteger(urlParams.get(2))));
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
