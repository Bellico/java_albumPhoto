package command;

import javax.servlet.http.HttpServletRequest;

public class actiondetest implements Command {

    @Override
    public String getCommandName() {
        return "addCommand";
    }

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        return new ActionFlow("test", "index.html", false);
    }
}
