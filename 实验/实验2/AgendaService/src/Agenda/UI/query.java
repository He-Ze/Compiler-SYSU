package Agenda.UI;

public class query implements Commamd{
    public int check(String[] command){
        if(command.length==5)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
