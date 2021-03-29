package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public class clear implements Commamd{
    public boolean check(String[] command){
        if(command.length==3)
            return true;
        else
            return false;
    }
    public void exec(String[] command, List<User> users){

    }
}
