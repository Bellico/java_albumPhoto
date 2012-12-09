package filter;

import bdd.RightMap;
import bean.AlbumBean;
import bean.RightBean;
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

@WebFilter(filterName = "albumFilter", urlPatterns = {"/albums/*"})
public class AlbumFilter extends FilterFunctions implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        HashMap<Integer, String> urlParams = Tools.parseUrl(request.getRequestURI());

        if (urlParams.get(1) != null) {
            UserBean userSession = isConnect(session);
            if (urlParams.get(1).equals("mesAlbums")) {
                if (userSession == null) {
                    sendError(request, "Veuillez vous connecter pour voir vos albums.");
                }
            } else {
                int numalbum = Tools.toInteger(urlParams.get(1));
                if (numalbum >= 0) {
                    AlbumBean album = getAlbum(numalbum);
                    if (album == null) {
                        sendError(request, "Cette album n'existe pas.");
                    } else {
                        if (album.getIdStatut() != 0) {
                            if (userSession == null) {
                                sendError(request, "Cet album est privé. Veuillez vous connecter pour le visualiser.");
                            } else if (userSession.getIdUser() != album.getIdUser()) {
                                RightMap mapRight = new RightMap();
                                RightBean right = mapRight.get(userSession.getIdUser(), album.getIdAlbum());
                                if (right == null) {
                                    sendError(request, "Cet album est privé. Vous n'avez aucun droit sur celui-ci.");
                                }
                            }
                        }
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
