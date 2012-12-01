package command;

import java.util.HashMap;

public class ActionFlow {

    private String path;
    HashMap<String, String> attrPage;
    private boolean redirect;

    public ActionFlow(String path, HashMap<String, String> attrPage, boolean redirect) {
        this.path = path;
        this.attrPage = attrPage;
        this.redirect = redirect;
    }

    public ActionFlow(String path, boolean redirect) {
        this.path = path;
        this.attrPage = null;
        this.redirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, String> getAttrPage() {
        return attrPage;
    }

    public void setAttrPage(HashMap<String, String> attrPage) {
        this.attrPage = attrPage;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}
