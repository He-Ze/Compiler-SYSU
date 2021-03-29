package Agenda.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userName;
    private final String password;
    public List<Agent> agents = new ArrayList<>();

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public boolean checkUser(String name, String password) {
        return name.equals(this.userName) && password.equals(this.password);
    }

    public String getUserName() {
        return userName;
    }

    public boolean addAgent(int indexOfUser1, int indexOfUser2, String user1, String user2, String[] date1, String[] date2, String title, List<User> users) {
        Agent newAgent = new Agent(user1, user2, date1, date2, title);
        Agent newAgent2 = new Agent(user2, user1, date1, date2, title, newAgent.getID());
        if (hasConflict(indexOfUser1, users, newAgent))
            return false;
        if (hasConflict(indexOfUser1, users, newAgent2))
            return false;
        agents.add(newAgent);
        users.get(indexOfUser2).agents.add(newAgent2);
        return true;
    }

    public boolean hasConflict(int indexOfUser, List<User> users, Agent newAgent) {
        for (int j = 0; j < users.get(indexOfUser).agents.size(); j++) {
            if ((newAgent.getDate1().after(users.get(indexOfUser).agents.get(j).getDate1()) && newAgent.getDate1().before(users.get(indexOfUser).agents.get(j).getDate2())) ||
                    (newAgent.getDate2().after(users.get(indexOfUser).agents.get(j).getDate1()) && newAgent.getDate2().before(users.get(indexOfUser).agents.get(j).getDate2()))) {
                System.out.println("  此日程与其它日程时间冲突，添加失败");
                return true;
            }
        }
        return false;
    }
}
