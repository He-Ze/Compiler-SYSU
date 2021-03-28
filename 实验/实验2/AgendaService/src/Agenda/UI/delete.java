package Agenda.UI;

public class delete implements Commamd{
    public int check(String[] command){
        if(command.length==4)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
