package command;

import javax.servlet.http.HttpServletRequest;

abstract public class Command {

    abstract public void actionPerform(HttpServletRequest request);

    protected void setView(HttpServletRequest request, String path) {
        request.setAttribute("view", path);
    }
}