package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class Help implements Command {


    private View view;

    public Help(View view){
        this.view=view;
    }



    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String command) {

            view.write("Существующие команды:");

        view.write("\tconnect|database|userName|password");
        view.write("\t\tдля подключения к базе данных");

            view.write("\tlist");
            view.write("\t\tдля получения списка всех таблиц базы, к которой подключились");

            view.write("\tfind|tableName");
            view.write("\t\tдля получения содержимого таблицы 'tableName'");

             view.write("\tclear|tableName");
            view.write("\t\tдля очистки содержимого таблицы 'tableName'");

             view.write("\tcreate|tableName|column1|value1|....columnN|valueN");
             view.write("\t\tдля создания записи в 'tableName'");

            view.write("\thelp");
            view.write("\t\tдля вывода этого списка на экран");

            view.write("\texit");
            view.write("\t\tдля выхода из программы");


    }
}
