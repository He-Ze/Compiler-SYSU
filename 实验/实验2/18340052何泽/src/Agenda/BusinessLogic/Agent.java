package Agenda.BusinessLogic;

import java.util.Calendar;
import java.util.Random;

/**
 * 包含一个日程的所有信息的类
 */
public class Agent {
    private final String user1;
    private final String user2;
    private final Calendar date1 = Calendar.getInstance();
    private final Calendar date2 = Calendar.getInstance();
    private final String title;
    private final int ID;

    /**
     * 实例化一个日程的构造方法，随机生成ID
     *
     * @param user1 当前用户用户名
     * @param user2 被邀请用户用户名
     * @param date1 开始时间
     * @param date2 结束时间
     * @param title 标签
     */
    public Agent(String user1, String user2, String[] date1, String[] date2, String title) {
        Random r = new Random();
        this.user1 = user1;
        this.user2 = user2;
        this.date1.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(date1[3]), Integer.parseInt(date1[4]));
        this.date2.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(date2[3]), Integer.parseInt(date2[4]));
        this.title = title;
        this.ID = r.nextInt(100);
    }

    /**
     * 实例化一个日程的构造方法，使用传入的ID
     *
     * @param user1 当前用户用户名
     * @param user2 被邀请用户用户名
     * @param date1 开始时间
     * @param date2 结束时间
     * @param title 标签
     * @param ID    想要设置的ID
     */
    public Agent(String user1, String user2, String[] date1, String[] date2, String title, int ID) {
        this.user1 = user1;
        this.user2 = user2;
        this.date1.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(date1[3]), Integer.parseInt(date1[4]));
        this.date2.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(date2[3]), Integer.parseInt(date2[4]));
        this.title = title;
        this.ID = ID;
    }

    /**
     * 打印某一日程信息
     */
    public void printInfo() {
        System.out.println("  您将在" + this.date1.get(Calendar.YEAR) + "年" + this.date1.get(Calendar.MONTH) + "月" +
                this.date1.get(Calendar.DATE) + "日" + this.date1.get(Calendar.HOUR_OF_DAY) + ":" + this.date1.get(Calendar.MINUTE) +
                "至" + this.date2.get(Calendar.YEAR) + "年" + this.date2.get(Calendar.MONTH) + "月" +
                this.date2.get(Calendar.DATE) + "日" + this.date2.get(Calendar.HOUR_OF_DAY) + ":" + this.date2.get(Calendar.MINUTE) +
                "与" + user2 + "有安排，标签为" + title + ",ID为" + this.ID);
    }

    /**
     * 获取当前日程开始日期
     *
     * @return 当前日程开始日期 date 1
     */
    public Calendar getDate1() {
        return date1;
    }

    /**
     * 获取当前日程结束日期
     *
     * @return 当前日程结束日期 date 2
     */
    public Calendar getDate2() {
        return date2;
    }

    /**
     * 获取当前日程ID
     *
     * @return 当前日程ID id
     */
    public int getID() {
        return ID;
    }

    /**
     * 获取当前日程创建者用户名
     *
     * @return 当前日程创建者用户名
     */
    public String getUser1() {
        return user1;
    }

    /**
     * 获取当前日程被邀请者用户名
     *
     * @return 当前日程被邀请者用户名
     */
    public String getUser2() {
        return user2;
    }
}
