package Agenda.UI;

public class Register implements Commamd{
    public int check(String[] command){
        if(command.length==3)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
