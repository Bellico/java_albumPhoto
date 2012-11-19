package command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand extends Command {

    @Override
    public void actionPerform(HttpServletRequest request) {
        setView(request,"error.jsp");
    }
}
