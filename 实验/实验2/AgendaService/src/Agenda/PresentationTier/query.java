package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.Calendar;
import java.util.List;

/**
 * The type Query.
 */
public class query implements Command {
    public boolean check(String[] command) {
        return command.length == 5;
    }

    public void exec(String[] command, List<User> users) {
        int i;
        String[] date1 = command[3].split("-");
        String[] date2 = command[4].split("-");
        Calendar dateBegin = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateBegin.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(date1[3]), Integer.parseInt(date1[4]));
        dateEnd.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(date2[3]), Integer.parseInt(date2[4]));
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(command[1])) {
                break;
            }
        }
        if (i == users.size()) {
            System.out.println("  该用户不存在，请输入正确的用户名，输入help以获得提示");
        } else {
            if (users.get(i).checkUser(command[1], command[2])) {
                if (users.get(i).agents.size() == 0) {
                    System.out.println("  该用户目前无日程安排");
                } else {
                    for (int j = 0; j < users.get(i).agents.size(); j++) {
                        if (dateBegin.before(users.get(i).agents.get(j).getDate1()) && dateEnd.after(users.get(i).agents.get(j).getDate2())) {
                            users.get(i).agents.get(j).printInfo();
                        }
                    }
                }
            } else {
                System.out.println("  密码错误，请输入正确的密码，输入help以获得提示");
            }
        }
    }
}
