package Agenda.BusinessLogic;

import java.util.Calendar;
import java.util.Random;

public class Agent {
    private final String user1;
    private final String user2;
    private final Calendar date1 = Calendar.getInstance();
    private final Calendar date2 = Calendar.getInstance();
    private final String title;
    private final int ID;

    public Agent(String user1, String user2, String[] date1, String[] date2, String title) {
        Random r = new Random();
        this.user1 = user1;
        this.user2 = user2;
        this.date1.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(date1[3]), Integer.parseInt(date1[4]));
        this.date2.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(date2[3]), Integer.parseInt(date2[4]));
        this.title = title;
        this.ID = r.nextInt(100);
    }

    public Agent(String user1, String user2, String[] date1, String[] date2, String title, int ID) {
        this.user1 = user1;
        this.user2 = user2;
        this.date1.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(date1[3]), Integer.parseInt(date1[4]));
        this.date2.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(date2[3]), Integer.parseInt(date2[4]));
        this.title = title;
        this.ID = ID;
    }

    //add t1 123 t3 2021-3-20-8-30 2021-3-20-9-30 test1
    //add t2 123 t3 2021-3-21-8-30 2021-3-21-9-30 test2
    //query t3 123 2021-3-19-8-0 2021-4-20-8-0
    public void printInfo() {
        System.out.println("  您将在" + this.date1.get(Calendar.YEAR) + "年" + this.date1.get(Calendar.MONTH) + "月" +
                this.date1.get(Calendar.DATE) + "日" + this.date1.get(Calendar.HOUR_OF_DAY) + ":" + this.date1.get(Calendar.MINUTE) +
                "至" + this.date2.get(Calendar.YEAR) + "年" + this.date2.get(Calendar.MONTH) + "月" +
                this.date2.get(Calendar.DATE) + "日" + this.date2.get(Calendar.HOUR_OF_DAY) + ":" + this.date2.get(Calendar.MINUTE) +
                "与" + user2 + "有安排，标签为" + title + ",ID为" + this.ID);
    }

    public Calendar getDate1() {
        return date1;
    }

    public Calendar getDate2() {
        return date2;
    }

    public int getID() {
        return ID;
    }
}
