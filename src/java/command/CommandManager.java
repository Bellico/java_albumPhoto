package command;

import java.util.HashMap;

public class CommandManager {

    protected static HashMap<String, Command> cmds = new HashMap<String, Command>();

    public static void init() {
        cmds.put("upload", new UploadCommand());
        cmds.put("connexion", new ConnexionCommand());
    }

    public static Command getCommand(String commandName) {
        return cmds.get(commandName);
    }
}
