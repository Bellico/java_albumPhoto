package command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand extends Command {

    @Override
    public boolean actionPerform(HttpServletRequest request) {
        setView(request,"error.jsp");
        return true;
    }
}
