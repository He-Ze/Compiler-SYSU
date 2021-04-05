package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * 删除某一日程
 */
public class delete implements Command {
    public boolean check(String[] command) {
        return command.length == 4;
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
                for (int indexOfAnother = 0; indexOfAnother < users.get(indexOfMe).getAgents().size(); indexOfAnother++) {
                    if (users.get(indexOfMe).getAgents().get(indexOfAnother).getID() == Integer.parseInt(command[3])) {   //给自己删除
                        deleteAnotherPeopleAgent(users, indexOfMe, indexOfAnother);      //给对方删除
                        break;
                    }
                }
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }
    }

    /*给对方删除某一日程*/
    static void deleteAnotherPeopleAgent(List<User> users, int indexOfMe, int indexOfAnother) {
        for(int k=0;k<users.size();k++){
            for(int p = 0; p< users.get(k).getAgents().size(); p++){
                if(users.get(k).getAgents().get(p).getUser2().equals(users.get(indexOfMe).getAgents().get(indexOfAnother).getUser1())){
                    users.get(k).getAgents().remove(p);
                }
            }
        }
        users.get(indexOfMe).getAgents().remove(indexOfAnother);
    }
}
