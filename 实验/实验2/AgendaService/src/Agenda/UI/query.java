package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public class query implements Commamd{
    public boolean check(String[] command){
        if(command.length==5)
            return true;
        else
            return false;
    }
    public void exec(String[] command, List<User> users){

    }
}
