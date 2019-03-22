package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import ua.com.juja.sqlcmd.controller.Main;

import java.io.*;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class integrationTest {

    private ConfigurableInputStream in;
    private  ByteArrayOutputStream out;


    @Before
    public  void setup() {

        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn( in );
        System.setOut( new PrintStream( out ) );


    }

//
//    @Before
//    public void clearIn(){
//        try {
//            in.reset();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }



    @Test

    public void testHelp() throws SQLException, ClassNotFoundException {

        in.add( "help" );



        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Существующие команды:\r\n" +
                "\tconnect|database|userName|password\r\n" +
                "\t\tдля подключения к базе данных\r\n" +
                "\tlist\r\n" +
                "\t\tдля получения списка всех таблиц базы, к которой подключились\r\n" +
                "\tfind|tableName\r\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\r\n" +
                "\thelp\r\n" +
                "\t\tдля вывода этого списка на экран\r\n" +
                "\texit\r\n" +
                "\t\tдля выхода из программы\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "До скорой встречи!\r\n", getData() );

    }

    public String getData() {
        try {

            String result=new String( out.toByteArray(), "UTF-8" );
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return  e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Test
    public void testExit() throws SQLException, ClassNotFoundException {


        in.add( "exit" );


        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "До скорой встречи!\r\n", getData() );

    }


    @Test
    public void testListWithotConnect() throws SQLException, ClassNotFoundException {


        in.add( "list" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Вы не можете пользлваться командой list пока не используете комманду connect|database|userName|password\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "До скорой встречи!\r\n", getData() );

    }


    @Test
    public void testFindWithotConnect() throws SQLException, ClassNotFoundException {


        in.add( "find|user" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Вы не можете пользлваться командой find|user пока не используете комманду connect|database|userName|password\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "До скорой встречи!\r\n", getData() );

    }

    @Test
    public void testUnsupported() throws SQLException, ClassNotFoundException {


        in.add( "unsupported" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Вы не можете пользлваться командой unsupported пока не используете комманду connect|database|userName|password\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "До скорой встречи!\r\n", getData() );

    }
    @Test
    public void testUnsupportedAfterConnect() throws SQLException, ClassNotFoundException {

        in.add( "connect|sqlcmd|postgres|admin" );
        in.add( "unsupported" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Успех!\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "Несуществующая команда!unsupported\r\n" +
                        "Введи команду (или help для помощи):\r\n"+
                "До скорой встречи!\r\n", getData() );

    }
    @Test
    public void testListAfterConnect() throws SQLException, ClassNotFoundException {

        in.add( "connect|sqlcmd|postgres|admin" );
        in.add( "list" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Успех!\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "[test, user]\r\n" +
                "Введи команду (или help для помощи):\r\n"+
                "До скорой встречи!\r\n", getData() );

    }
    @Test
    public void testFindAfterConnect() throws SQLException, ClassNotFoundException {

        in.add( "connect|sqlcmd|postgres|admin" );
        in.add( "find|user" );
        in.add( "exit" );

        Main.main(new String[0] );


        assertEquals( "Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\r\n" +
                "Успех!\r\n" +
                "Введи команду (или help для помощи):\r\n" +
                "--------------------\r\n" +
                "|id|name|password|\r\n" +
                "--------------------\r\n" +
                "|70|Stiven|Pupkin|\r\n" +
                "Введи команду (или help для помощи):\r\n"+
                "До скорой встречи!\r\n", getData() );

    }

}

