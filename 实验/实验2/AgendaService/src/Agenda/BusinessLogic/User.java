package Agenda.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含一个用户的所有信息的类
 */
public class User {
    private final String userName;
    private final String password;
    private final List<Agent> agents = new ArrayList<>();

    /**
     * 创建一个新用户
     *
     * @param name     用户名
     * @param password 密码
     */
    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    /**
     * 检查密码是否正确
     *
     * @param name     用户名
     * @param password 密码
     * @return 密码是否正确
     */
    public boolean checkUser(String name, String password) {
        return name.equals(this.userName) && password.equals(this.password);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 给此用户添加一个日程
     *
     * @param indexOfUser1 当前用户在用户列表中的索引值
     * @param indexOfUser2 被邀请用户在用户列表中的索引值
     * @param user1        当前用户用户名
     * @param user2        被邀请用户用户名
     * @param date1        开始时间
     * @param date2        结束时间
     * @param title        标签
     * @param users        用户列表
     * @return 是否添加成功
     */
    public boolean addAgent(int indexOfUser1, int indexOfUser2, String user1, String user2, String[] date1, String[] date2, String title, List<User> users) {
        Agent newAgent = new Agent(user1, user2, date1, date2, title);
        Agent newAgent2 = new Agent(user2, user1, date1, date2, title, newAgent.getID());
        /*判断想要添加的日程和已存在的日程是否有冲突*/
        if (hasConflict(indexOfUser1, users, newAgent))
            return false;
        if (hasConflict(indexOfUser1, users, newAgent2))
            return false;
        getAgents().add(newAgent);
        users.get(indexOfUser2).getAgents().add(newAgent2);
        return true;
    }

    /**
     * 判断想要添加的日程和某用户已存在的日程是否有冲突
     *
     * @param indexOfUser 被查询的用户
     * @param users       用户列表
     * @param newAgent    想添加的新日程
     * @return 是否有冲突
     */
    public boolean hasConflict(int indexOfUser, List<User> users, Agent newAgent) {
        for (int j = 0; j < users.get(indexOfUser).getAgents().size(); j++) {
            if ((newAgent.getDate1().after(users.get(indexOfUser).getAgents().get(j).getDate1()) && newAgent.getDate1().before(users.get(indexOfUser).getAgents().get(j).getDate2())) ||
                    (newAgent.getDate2().after(users.get(indexOfUser).getAgents().get(j).getDate1()) && newAgent.getDate2().before(users.get(indexOfUser).getAgents().get(j).getDate2()))) {
                System.out.println("  此日程与其它日程时间冲突，添加失败");
                return true;
            }
        }
        return false;
    }

    /**
     * 获取日程列表
     */
    public List<Agent> getAgents() {
        return agents;
    }
}
