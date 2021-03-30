import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Agenda.UI.*;
import Agenda.BusinessLogic.*;

public class AgendaService {
    public static void main(String[] args) {
        printHelp();
        System.out.print("$ ");
        Scanner input = new Scanner(System.in);
        String[] commands = input.nextLine().split(" ");
        Command c = null;
        List<User> users = new ArrayList<>();
        while (!commands[0].equalsIgnoreCase("quit")) {
            if(commands[0].equalsIgnoreCase("batch")){
                System.out.print("$ ");
                try {
                    FileReader m= null;
                    m = new FileReader(commands[1]);
                    BufferedReader reader=new BufferedReader(m);
                    String nextLine= null;
                    nextLine = reader.readLine();
                    while (!(nextLine ==null)){
                        System.out.println(nextLine);
                        commands=nextLine.split(" ");
                        c=switchCommand(commands);
                        if(c==null)
                            break;
                        if (!c.check(commands)) {
                            System.out.println("  参数个数不正确，请输入正确指令参数，输入help以获得提示");
                        }
                        c.exec(commands, users);
                        System.out.print("$ ");
                        nextLine=reader.readLine();
                    }
                    break;
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                c = switchCommand(commands);
                if (c == null)
                    break;
                if (!c.check(commands)) {
                    System.out.println("  参数个数不正确，请输入正确指令参数，输入help以获得提示");
                }
                c.exec(commands, users);
                System.out.print("$ ");
                input = new Scanner(System.in);
                commands = input.nextLine().split(" ");
            }
        }
    }

    static void printHelp() {
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

    static Command switchCommand (String[] commands){
        switch (commands[0].toLowerCase()) {
            case "register":
                return new Register();
            case "add":
                return new add();
            case "query":
                return new query();
            case "delete":
                return new delete();
            case "clear":
                return new clear();
            case "help":
                printHelp();
                break;
            default:
                System.out.println("  指令错误，请输入正确指令，输入help以获得提示");
        }
        return null;
    }
}
