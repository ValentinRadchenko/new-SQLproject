package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class Connect implements Command {

    private static final String COMMAND_SAMPLE="connect|sqlcmd|postgres|admin";

    private final DatabaseManager manager;
    private final View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith( "connect|" );
    }

    @Override
    public void process(String command) {

                try {
                    String string = command;
                    String[] data = string.split("\\|");
                    if (data.length != count()) {
                        throw new IllegalArgumentException(String.format("Неверно количество параметров разделенных знаком '|', ожидается %s но есть: %s", count(),data.length));
                    }
                    String databaseName = data[1];
                    String userName = data[2];
                    String password = data[3];

                    manager.connect(databaseName, userName, password);

                    view.write("Успех!");
                } catch (Exception e) {
                    printError(e);
                }
            }

    private int count() {
        return COMMAND_SAMPLE.split( "\\|" ).length;
    }


    private void printError(Exception e) {
            String message = /*e.getClass().getSimpleName() + ": " + */ e.getMessage();
            Throwable cause = e.getCause();
            if (cause != null) {
                message += " " + /*cause.getClass().getSimpleName() + ": " + */ cause.getMessage();
            }
            view.write("Неудача! по причине: " + message);
            view.write("Повтори попытку.");
        }


    }

