package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class Create implements Command {


    private final DatabaseManager manager;
    private final View view;

    public Create(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith( "create|" );
    }

    @Override
    public void process(String command) {

        String[] data = command.split( "\\|" );

        DataSet dataSet=new DataSet();
        String tablename=data[1];



        if(data.length % 2 !=0){
            throw new IllegalArgumentException( "Должно быть четное количество элементов а у вас "+
                    command );
        }




        for (int i = 1; i <data.length/2 ; i++) {

            String columnName=data[i*2];
            String value=data[i*2+1];

            dataSet.put( columnName,value );


        }

        manager.create( tablename,dataSet );
       view.write(String.format("Таблица %s создана успешно %s",tablename,dataSet) );
    }
}
