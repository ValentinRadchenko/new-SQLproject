package ua.com.juja.sqlcmd.Controller.Command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.sqlcmd.controller.command.Clear;
import ua.com.juja.sqlcmd.controller.command.Find;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;



public class ClearTest {



    private DatabaseManager manager;
    private View view;
    private Clear command;

    @Before
    public void setup(){

        manager= mock(DatabaseManager.class);
        view=mock( View.class );
        command= new Clear( manager, view );
    }


    @Test
    public void TestClear(){

        command.process( "clear|user" );

        verify( manager ).clear( "user" );
        shouldPrint( "[Таблица очищена!]" );


    }

    private void shouldPrint(String expected) {
        ArgumentCaptor<String> captor=ArgumentCaptor.forClass( String.class );
        verify( view,atLeastOnce() ).write(captor.capture() );
        assertEquals( expected,captor.getAllValues().toString());
    }

    @Test
    public void testCanProcessClear(){


        Boolean canProcess=command.canProcess( "clear|user" );

        assertTrue(canProcess);
    }
    @Test
    public void testCantProcessClearString(){


        Boolean canProcess=command.canProcess( "clear" );

        assertFalse(canProcess);
    }

    @Test
    public void testProcessClear(){

        try {
            command.process( "clear" );
        }catch (IllegalArgumentException e){
            assertEquals("Введено нверное количество параметров",e.getMessage());
        }

    }


}
