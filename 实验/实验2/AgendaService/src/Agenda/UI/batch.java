package Agenda.UI;

public class batch implements Commamd{
    public int check(String[] command){
        if(command.length==2)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
