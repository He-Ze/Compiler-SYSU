import java.util.Scanner;
import Agenda.UI.*;

public class AgendaService {
    public static void main(String[] args){
        System.out.println("==============================================================");
        System.out.println("Welcome to HeZe's agenda manage programe.");
        System.out.print("You can type the commands below:\n" +
                "1. register [userName] [password]\n" +
                "2. add      [userName] [password] [other] [start] [end] [title]\n" +
                "3. query    [userName] [password] [start] [end]\n" +
                "4. delete   [userName] [password] [meetingId]\n" +
                "5. clear    [userName] [password]\n" +
                "6. batch    [fileName]\n" +
                "7. quit\n");
        System.out.println("==============================================================");
        System.out.print("$ ");
        Scanner input = new Scanner(System.in);
        String[] commands = input.nextLine().split(" ") ;
        Commamd c = null;
        while (!commands[0].equals("quit")){
            switch (commands[0]) {
                case "register":
                    c = new Register();
                    break;
                case "add":
                    c=new add();
                    break;
                case "query":
                    c=new query();
                    break;
                case "delete":
                    c=new delete();
                    break;
                case "clear":
                    c=new clear();
                    break;
                case "batch":
                    c=new batch();
                    break;
                default:
                    System.out.println("------------------------------------");
                    System.out.println("请输入正确指令");
                    System.out.println("------------------------------------");
                    break;
            }
            if(c.check(commands)==0){
                System.out.println("------------------------------------");
                System.out.println("参数个数不正确，请输入正确指令参数");
                System.out.println("------------------------------------");
            }
            System.out.print("$ ");
            input = new Scanner(System.in);
            commands = input.nextLine().split(" ");


        }
    }
}
