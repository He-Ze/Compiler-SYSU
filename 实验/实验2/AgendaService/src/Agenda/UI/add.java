package Agenda.UI;

import Agenda.BusinessLogic.User;
import java.util.List;

public class add implements Commamd{
    public boolean check(String[] command){
        return command.length == 7;
    }
    public void exec(String[] command, List<User> users){
        int i;
        for(i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(command[1])){
                break;
            }
        }
        if(i== users.size()-1){
            System.out.println("  --------------------------------------------------------------");
            System.out.println("  该用户不存在，请输入正确的用户名，输入help以获得提示");
            System.out.println("  --------------------------------------------------------------");
        }
        else{
            if(users.get(i).checkUser(command[1],command[2])){
                if(users.get(i).addAgent(users.get(i).getUserName(),command[3],command[4].split("-"),command[5].split("-"),command[6],users )) {
                    System.out.println("  --------------------------------------------------------------");
                    System.out.println("  日程添加成功！");
                    users.get(i).agents.get(users.get(i).agents.size() - 1).printInfo();
                    System.out.println("  --------------------------------------------------------------");
                }
            }
            else{
                System.out.println("  --------------------------------------------------------------");
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
                System.out.println("  --------------------------------------------------------------");
            }
        }

    }
}
