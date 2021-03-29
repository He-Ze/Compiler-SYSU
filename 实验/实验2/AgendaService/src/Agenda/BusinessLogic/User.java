package Agenda.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userName;
    private final String password;
    public List<Agent> agents = new ArrayList<>();
    public User(String name, String password){
        this.userName=name;
        this.password=password;
    }
    public boolean checkUser(String name, String password){
        return name.equals(this.userName) && password.equals(this.password);
    }

    public String getUserName() {
        return userName;
    }
    public boolean addAgent(String user1,String user2,String[] date1,String[] date2, String title,List<User> users){
        Agent newAgent =new Agent(user1,user2,date1,date2,title);
        for(int i=0;i<users.size();i++){
            for(int j=0;j<users.get(i).agents.size();j++) {
                if ( (newAgent.getDate1().after(users.get(i).agents.get(j).getDate1()) && newAgent.getDate1().before(users.get(i).agents.get(j).getDate2()))||
                        (newAgent.getDate2().after(users.get(i).agents.get(j).getDate1()) && newAgent.getDate2().before(users.get(i).agents.get(j).getDate2()))){
                    System.out.println("  --------------------------------------------------------------");
                    System.out.println("  此日程与其它日程时间冲突，添加失败");
                    System.out.println("  --------------------------------------------------------------");
                    return false;
                }
            }
        }
        agents.add(newAgent);
        return true;
    }
}
