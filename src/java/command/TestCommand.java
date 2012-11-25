package command;

import javax.servlet.http.HttpServletRequest;

public class TestCommand extends Command {

    @Override
    public boolean actionPerform(HttpServletRequest request) {
        setView(request,"test.jsp");
        return true;
    }
}
