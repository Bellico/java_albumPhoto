package command;

import java.util.HashMap;

public class CommandManager {

    protected static HashMap<String, Command> cmds = new HashMap<String, Command>();

    public static void init() {
        cmds.put("default", new DefaultCommand());
        cmds.put("error", new ErrorCommand());
        cmds.put("test", new TestCommand());
    }

    public static Command getCommand(String commandName) {
        return cmds.get(commandName);
    }
}
