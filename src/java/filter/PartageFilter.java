package filter;

import bean.AlbumBean;
import bean.UserBean;
import java.io.IOException;
import java.util.HashMap;
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
import tools.Tools;

@WebFilter(filterName = "partageFilter", urlPatterns = {"/partage/*"})
public class PartageFilter extends FilterFunctions implements Filter {

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
        HashMap<Integer, String> urlParams = Tools.parseUrl(request.getRequestURI());

        UserBean userSession = isConnect(session);
        if (userSession == null) {
            sendError(request, "Veuillez vous connecter pour acceder à cette page.");
        } else if (urlParams.get(1) != null) {
            int numalbum = Tools.toInteger(urlParams.get(1));
            if (numalbum >= 0) {
                AlbumBean album = getAlbum(numalbum);
                if (album == null) {
                    sendError(request, "Cette album n'existe pas.");
                } else if (userSession.getIdUser() != album.getIdUser()) {
                    sendError(request, "Vous n'avez aucun droit sur cet album.");
                }
            }
        }
        chain.doFilter(request, response);
    }
}
