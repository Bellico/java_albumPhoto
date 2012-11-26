package command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    
     public ActionFlow actionPerform(HttpServletRequest request);

}