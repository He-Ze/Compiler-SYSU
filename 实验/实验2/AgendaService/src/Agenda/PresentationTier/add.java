package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * The type Add.
 */
public class add implements Command {
    public boolean check(String[] command) {
        return command.length == 7;
    }

    public void exec(String[] command, List<User> users) {
        int indexOfUser1, indexOfUser2;
        for (indexOfUser1 = 0; indexOfUser1 < users.size(); indexOfUser1++) {
            if (users.get(indexOfUser1).getUserName().equals(command[1])) {
                break;
            }
        }
        for (indexOfUser2 = 0; indexOfUser2 < users.size(); indexOfUser2++) {
            if (users.get(indexOfUser2).getUserName().equals(command[3])) {
                break;
            }
        }
        if (indexOfUser1 == users.size()) {
            System.out.println("  该用户不存在，请输入正确的用户名，输入help以获得提示");
        } else {
            if (users.get(indexOfUser1).checkUser(command[1], command[2])) {
                if (users.get(indexOfUser1).addAgent(indexOfUser1, indexOfUser2, users.get(indexOfUser1).getUserName(), command[3], command[4].split("-"), command[5].split("-"), command[6], users)) {
                    System.out.println("  日程添加成功！");
                    users.get(indexOfUser1).agents.get(users.get(indexOfUser1).agents.size() - 1).printInfo();
                } else {
                    System.out.println("添加失败");
                }
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }

    }
}
