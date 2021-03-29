package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public class batch implements Command {
    public boolean check(String[] command) {
        return command.length == 2;
    }

    public void exec(String[] command, List<User> users) {

    }
}
