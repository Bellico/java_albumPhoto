package command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    public String getCommandName();

    public ActionFlow actionPerform(HttpServletRequest request);
}