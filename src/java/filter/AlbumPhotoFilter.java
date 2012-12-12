package filter;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import command.ICommand;
import command.UploadCommand;
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

@WebFilter(filterName = "albumPhotoFilter", urlPatterns = {"/" + UploadCommand.FOLDER_ALBUM + "/*"})
public class AlbumPhotoFilter extends FilterFunctions implements Filter {

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

        boolean access = true;
        String urlPhoto = urlParams.get(2);
        if (urlPhoto != null) {
            PhotoMap mapPhoto = new PhotoMap();
            PhotoBean photo = (PhotoBean) mapPhoto.getbyAttr("img", urlPhoto);
            if (photo != null) {
                AlbumMap mapAlbum = new AlbumMap();
                AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
                UserBean userSession = isConnect(session);
                if (isAdmin(session) == null) {
                    if (album.getIdStatut() != 0) {
                        if (userSession == null) {
                            access = false;
                        } else if (userSession.getIdUser() != album.getIdUser()) {
                            RightMap mapRight = new RightMap();
                            RightBean right = mapRight.get(userSession.getIdUser(), album.getIdAlbum());
                            if (right == null) {
                                access = false;
                            }
                        }
                    }
                }
            } else {
                access = false;
            }
        }
        if (!access) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            chain.doFilter(request, response);
        }
    }
}
