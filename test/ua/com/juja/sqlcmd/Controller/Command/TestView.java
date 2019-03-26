package ua.com.juja.sqlcmd.Controller.Command;

import ua.com.juja.sqlcmd.view.View;

public class TestView implements View {


    private String messages="";

    @Override
    public void write(String message) {
        messages+=message+"\n";
    }

    @Override
    public String read() {
        return null;
    }


    public String getContent() {
        return messages;
    }
}
