package command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand extends Command {

    @Override
    public void actionPerform(HttpServletRequest request) {
        setView(request,"index.jsp");
    }
}
