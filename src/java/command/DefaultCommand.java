package command;

import bean.PhotoBean;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import model.Upload;


public class DefaultCommand extends Command {

    @Override
    public boolean actionPerform(HttpServletRequest request) {
        setView(request, "index.jsp");
       return true;
    }
}
