package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class Connect implements Command {
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
                    if (data.length != 4) {
                        throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 3, но есть: " + data.length);
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

