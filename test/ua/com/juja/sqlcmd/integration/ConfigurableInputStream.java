package ua.com.juja.sqlcmd.integration;


import java.io.IOException;
import java.io.InputStream;

public class ConfigurableInputStream extends InputStream {


    private String line;
    //boolean endLine=false;
  boolean endline=false;
    @Override
    public int read() throws IOException {

        if(line.length()==0){

            return -1;
        }

        if(endline){
            endline=false;
            return -1;
        }




        char ch= line.charAt( 0 );
        line=line.substring( 1 );

        if(ch=='\n'){
            endline=true;
        }


 return (int)ch;   }

 public void add(String line){

        if(this.line==null){
            this.line=line;
        }else{
            this.line+="\r\n"+line;
        }

 }

 public synchronized void reset() throws IOException{

  line=null;
  endline=false;

 }


}

