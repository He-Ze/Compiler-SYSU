package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public class batch implements Commamd{
    public boolean check(String[] command){
        if(command.length==2)
            return true;
        else
            return false;
    }
    public void exec(String[] command, List<User> users){

    }
}
