package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public interface Command {
    boolean check(String[] command);

    void exec(String[] command, List<User> users);
}