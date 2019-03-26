package ua.com.juja.sqlcmd.Controller.Command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import ua.com.juja.sqlcmd.controller.command.Command;
import ua.com.juja.sqlcmd.controller.command.Find;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import javax.lang.model.util.Types;

import static org.junit.Assert.assertEquals;

public class FindTest {


    private DatabaseManager manager;
    private View view;

    @Before
    public void setup(){

    manager= mock(DatabaseManager.class);
    view=mock( View.class );
    }


    @Test
    public void Test(){

    Command command= new Find( manager, view );




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

}
