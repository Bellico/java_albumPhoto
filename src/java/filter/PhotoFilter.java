package filter;

import bean.PhotoBean;
import bean.RightBean;
import command.ICommand;
import command.UploadCommand;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "albumPhotoFilter", urlPatterns = {"/" + UploadCommand.FOLDER_ALBUM + "/*"})
public class PhotoFilter extends FilterFunctions implements Filter {

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
        String urlPhoto = urlParams.get(2);
        if (urlPhoto != null) {
            PhotoBean photo = (PhotoBean) mapPhoto.getbyAttr("img", urlPhoto);
            right = Right_On_Album(isConnect(), photo);
        }

        if (right == null || !right.isLire()) {
            response.sendRedirect("/AlbumPhoto/" + ICommand.PAGE_ERROR);
        } else {
            chain.doFilter(request, response);
        }

    }
}
