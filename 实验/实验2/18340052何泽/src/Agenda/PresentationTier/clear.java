package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * 清除某个用户的所有日程
 */
public class clear implements Command {
    public boolean check(String[] command) {
        return command.length == 3;
    }

    public void exec(String[] command, List<User> users) {
        /*获取自己在用户列表中的索引*/
        int indexOfMe;
        for (indexOfMe = 0; indexOfMe < users.size(); indexOfMe++) {
            if (users.get(indexOfMe).getUserName().equals(command[1])) {
                break;
            }
        }
        if (indexOfMe == users.size()) {
            System.out.println("  该用户不存在，请输入正确的用户名，输入help以获得提示");
        } else {
            if (users.get(indexOfMe).checkUser(command[1], command[2])) {
                for (int j = 0; j < users.get(indexOfMe).getAgents().size(); j++) {
                    /*给对方删除*/
                    delete.deleteAnotherPeopleAgent(users, indexOfMe, j);
                    /*给自己删除*/
                    users.get(indexOfMe).getAgents().remove(j);
                }
                System.out.println("  日程移除成功");
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }
    }
}
