package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * The type Delete.
 */
public class delete implements Command {
    public boolean check(String[] command) {
        return command.length == 4;
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
                    if (users.get(i).agents.get(j).getID() == Integer.parseInt(command[3])) {
                        deleteAnotherPeopleAgent(users, i, j);
                        break;
                    }
                }
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }
    }

    static void deleteAnotherPeopleAgent(List<User> users, int i, int j) {
        for(int k=0;k<users.size();k++){
            for(int p=0;p<users.get(k).agents.size();p++){
                if(users.get(k).agents.get(p).getUser2().equals(users.get(i).agents.get(j).getUser1())){
                    users.get(k).agents.remove(p);
                }
            }
        }
        users.get(i).agents.remove(j);
    }
}
