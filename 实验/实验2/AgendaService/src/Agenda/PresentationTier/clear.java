package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * The type Clear.
 */
public class clear implements Command {
    public boolean check(String[] command) {
        return command.length == 3;
    }

    public void exec(String[] command, List<User> users) {
        int i;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(command[1])) {
                break;
            }
        }
        if (i == users.size()) {
            System.out.println("  该用户不存在，请输入正确的用户名，输入help以获得提示");
        } else {
            if (users.get(i).checkUser(command[1], command[2])) {
                for (int j = 0; j < users.get(i).agents.size(); j++) {
                    delete.deleteAnotherPeopleAgent(users, i, j);
                }
                System.out.println("  日程移除成功");
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }
    }
}
