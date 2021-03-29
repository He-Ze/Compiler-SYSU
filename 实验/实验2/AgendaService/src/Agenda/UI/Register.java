package Agenda.UI;

import Agenda.BusinessLogic.User;
import java.util.List;

public class Register implements Commamd{
    public boolean check(String[] command){
        return command.length == 3;
    }
    public void exec(String[] command, List<User> users){
        User newUser = new User(command[1],command[2]);
        users.add(newUser);
        System.out.println("  ------------------------------------------------------------------");
        System.out.println("  欢迎"+command[1]+"!注册成功！");
        System.out.println("  ------------------------------------------------------------------");
    }
}
