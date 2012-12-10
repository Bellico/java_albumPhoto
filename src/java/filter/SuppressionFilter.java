package filter;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import command.ICommand;
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

@WebFilter(filterName = "suppressionFilter", urlPatterns = {"/supp/*"})
public class SuppressionFilter extends FilterFunctions implements Filter {

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

        AlbumMap mapAlbum = new AlbumMap();
        PhotoMap mapPhoto = new PhotoMap();
        RightMap mapRight = new RightMap();
        boolean allow = false;

        if (urlParams.get(1) != null && urlParams.get(2) != null) {
            if (isAdmin(session) != null) {
                allow = true;
            } else {
                UserBean userSession = isConnect(session);
                if (userSession != null) {
                    int num = Tools.toInteger(urlParams.get(2));
                    if (num >= 0) {
                        AlbumBean album = null;
                        if (urlParams.get(1).equals("album")) {
                            album = (AlbumBean) mapAlbum.getbyId(num);
                        } else if (urlParams.get(1).equals("photo")) {
                            PhotoBean photo = (PhotoBean) mapPhoto.getbyId(num);
                            album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
                        }
                        if (album != null) {
                            if (album.getIdUser() == userSession.getIdUser()) {
                                allow = true;
                            } else {
                                if (urlParams.get(1).equals("photo")) {
                                    RightBean rights = mapRight.get(userSession.getIdUser(), album.getIdAlbum());
                                    if (rights!= null && rights.isSupprimer()) {
                                        allow = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!allow) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            chain.doFilter(request, response);
        }
    }
}
