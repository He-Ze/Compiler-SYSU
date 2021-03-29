import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Agenda.UI.*;
import Agenda.BusinessLogic.*;

public class AgendaService {
    public static void main(String[] args){
        printHelp();
        System.out.print("$ ");
        Scanner input = new Scanner(System.in);
        String[] commands = input.nextLine().split(" ") ;
        Commamd c = null;
        List<User> users =new ArrayList<>();
        while (!commands[0].equalsIgnoreCase("quit")){
            switch (commands[0].toLowerCase()) {
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
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("  ------------------------------------------------------------------");
                    System.out.println("  指令错误，请输入正确指令，输入help以获得提示");
                    System.out.println("  ------------------------------------------------------------------");
                    break;
            }
            assert c != null;
            if(!c.check(commands)){
                System.out.println("  ------------------------------------------------------------------");
                System.out.println("  参数个数不正确，请输入正确指令参数，输入help以获得提示");
                System.out.println("  ------------------------------------------------------------------");
            }
            c.exec(commands,users);
            System.out.print("$ ");
            input = new Scanner(System.in);
            commands = input.nextLine().split(" ");
        }
    }
    static void printHelp(){
        System.out.println("====================================================================");
        System.out.println("Welcome to HeZe's agenda manage programe.");
        System.out.print("You can type the commands below:\n" +
                "1. register [userName] [password]\n" +
                "2. add      [userName] [password] [other] [start] [end] [title]\n" +
                "3. query    [userName] [password] [start] [end]\n" +
                "4. delete   [userName] [password] [meetingId]\n" +
                "5. clear    [userName] [password]\n" +
                "6. batch    [fileName]\n" +
                "7. help" +
                "8. quit\n" +
                "（注：时间请以\"年-月-日-时-分\"格式输入）\n");
        System.out.println("====================================================================");
    }
}
