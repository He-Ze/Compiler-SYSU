package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * 用户注册
 */
public class Register implements Command {
    public boolean check(String[] command) {
        return command.length == 3;
    }

    public void exec(String[] command, List<User> users) {
        User newUser = new User(command[1], command[2]);
        users.add(newUser);
        System.out.println("  欢迎" + command[1] + "!注册成功！");
    }
}
