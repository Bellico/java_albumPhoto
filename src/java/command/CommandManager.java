package command;

import java.util.HashMap;

public class CommandManager {

    private static HashMap<String, ICommand> cmds = new HashMap<String, ICommand>();

    public static void init() {
        cmds.put("index", new DefaultCommand());
        cmds.put("photos", new PhotosCommand());
        cmds.put("albums", new AlbumCommand());
        cmds.put("utilisateurs", new UsersCommand());
        cmds.put("error", new ErrorCommand());
        cmds.put("upload", new UploadCommand());
        cmds.put("connexion", new ConnexionCommand());
        cmds.put("inscription", new InscriptionCommand());
        cmds.put("partage", new PartageCommand());
        cmds.put("admin", new AdminCommand());
        cmds.put("supp", new SupressionCommand());
        cmds.put("modif", new ModificationCommand());
    }

    public static ICommand getCommand(String commandName) {
        return cmds.get(commandName);
    }
}
