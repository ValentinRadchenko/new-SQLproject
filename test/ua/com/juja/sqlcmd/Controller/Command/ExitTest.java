package ua.com.juja.sqlcmd.Controller.Command;

import com.sun.javaws.exceptions.ExitException;
import org.junit.Test;
import ua.com.juja.sqlcmd.controller.command.Command;
import ua.com.juja.sqlcmd.controller.command.Exit;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;

public class ExitTest {


    private TestView view=new TestView();

    @Test


    public void testCanProcessExitString(){
    Command command=new Exit( view );

    Boolean canProcess=command.canProcess( "exit" );

assertTrue(canProcess);
}
@Test
    public void testCantProcessExitString(){
        Command command=new Exit( view );

        Boolean canProcess=command.canProcess( "eww" );

        assertFalse(canProcess);
    }

    @Test
    public void testProcessExitCommand_ExitExceprion(){

        Command command=new Exit( view );

      try {
         command.process( "exit" );
         fail("Exitexception");
      }catch(ua.com.juja.sqlcmd.controller.command.ExitException e){
//cbcvbcvb
           }
        assertEquals("До скорой встречи!\n", view.getContent());

    }

}
