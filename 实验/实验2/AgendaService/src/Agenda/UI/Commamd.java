package Agenda.UI;

import Agenda.BusinessLogic.User;

import java.util.List;

public interface Commamd {
    public boolean check(String[] command);
    public void exec(String[] command, List<User> users);
}