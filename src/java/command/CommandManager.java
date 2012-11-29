package command;

import java.util.HashMap;

public class CommandManager {

    protected static HashMap<String, Command> cmds = new HashMap<String, Command>();

    public static void init() {
        cmds.put("index", new DefaultCommand());
        cmds.put("images", new ImagesCommand());
        cmds.put("albums", new AlbumCommand());
        //cmds.put("details", new DetailsCommand());
        cmds.put("utilisateurs", new UsersCommand());
        //cmds.put("ajoutAlbum", new AddAlbumCommand());
        cmds.put("error", new ErrorCommand());
        cmds.put("upload", new UploadCommand());
        cmds.put("connexion", new ConnexionCommand());
    }

    public static Command getCommand(String commandName) {
        return cmds.get(commandName);
    }
}
