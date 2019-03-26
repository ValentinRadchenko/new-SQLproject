package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.Arrays;

/**
 * Created by indigo on 25.08.2015.
 */
public class MainController {

    private final Command[] commands;
    private View view;


    public MainController(View view, DatabaseManager manager) {

        this.commands = new Command[]{
                new Connect(manager, view),
                new Exit( view )
               ,new Help( view )
               ,new isConnected(manager,view)
                ,new Clear( manager, view )
                ,new Create( manager, view )
                ,new List( manager, view )
               ,new Find( manager, view ),
                new Unsupported( view )};
       this.view = view;
   }
    public void run() {
        try {
            doWork();
            return;
        } catch (ExitException e) {
            //jkjklj
        }

    }

    private void doWork() {
        view.write( "Привет юзер!" );
        view.write( "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password" );


        while (true) {
            String input = view.read();
            if (input == null) {
                new Exit( view ).process( input );
            }
            for (Command command : commands) {
                if (command.canProcess( input )) {
                    command.process( input );
                    break;
                }
            }
            view.write( "Введи команду (или help для помощи):" );
        }
    }
}
