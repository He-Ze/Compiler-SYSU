package Agenda.UI;

public class add implements Commamd{
    public int check(String[] command){
        if(command.length==7)
            return 1;
        else
            return 0;
    }
    public void exec(String[] command){

    }
}
