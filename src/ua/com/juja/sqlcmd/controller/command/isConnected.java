package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class isConnected implements Command {
    private final DatabaseManager manager;
    private final View view;

    public isConnected(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected();
    }

    @Override
    public void process(String command) {
      view.write( String.format( "Вы не можете пользлваться командой %s пока не используете комманду connect|database|userName|password",command) );
    }
}
