package Agenda.UI;

public class clear implements Commamd{
    public int check(String[] command){
        if(command.length==3)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
