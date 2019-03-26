package ua.com.juja.sqlcmd.Controller.Command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ua.com.juja.sqlcmd.controller.command.Command;
import ua.com.juja.sqlcmd.controller.command.Exit;
import ua.com.juja.sqlcmd.controller.command.Find;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import javax.lang.model.util.Types;

public class FindTest {


    private DatabaseManager manager;
    private View view;
    private Find command;

    @Before
    public void setup(){

    manager= mock(DatabaseManager.class);
    view=mock( View.class );
    command= new Find( manager, view );
    }


    @Test
    public void Test(){

        when( manager.getTableColumns( "user" ) ).thenReturn( new String[]{"id","name","password"} );


        DataSet user1=new DataSet();
        user1.put( "id",45 );
        user1.put( "name","Stiv" );
        user1.put( "password","dddddd" );

        DataSet user2=new DataSet();
        user2.put( "id",433 );
        user2.put( "name","Stiverr" );
        user2.put( "password","dd3333d" );
        DataSet [] data=new DataSet[]{user1,user2};
      when( manager.getTableData( "user" ) ).thenReturn( data );


        command.process( "find|user" );


        ArgumentCaptor<String> captor=ArgumentCaptor.forClass( String.class );
       verify( view,atLeastOnce() ).write(captor.capture() );
  assertEquals("[--------------------, |id|name|password|, --------------------, |45|Stiv|dddddd|, |433|Stiverr|dd3333d|]",captor.getAllValues().toString());


    }

@Test
    public void testCanProcessFind(){


        Boolean canProcess=command.canProcess( "find|user" );

        assertTrue(canProcess);
    }
    @Test
    public void testCantProcessFindString(){


        Boolean canProcess=command.canProcess( "find" );

        assertFalse(canProcess);
    }

    @Test
    public void testProcessFindCommand_QWE(){



        Boolean canProcess=command.canProcess( "qwe|user" );

        assertFalse(canProcess);

    }
    @Test
    public void TestEmpty(){

        when( manager.getTableColumns( "user" ) ).thenReturn( new String[]{"id","name","password"} );
        DataSet [] data=new DataSet[0];
        when( manager.getTableData( "user" ) ).thenReturn( data );
        command.process( "find|user" );

        ArgumentCaptor<String> captor=ArgumentCaptor.forClass( String.class );
        verify( view,atLeastOnce() ).write(captor.capture() );
        assertEquals("[--------------------, |id|name|password|, --------------------]",captor.getAllValues().toString());
    }

}
