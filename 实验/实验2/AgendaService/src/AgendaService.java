import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Agenda.PresentationTier.*;
import Agenda.BusinessLogic.*;

/**
 * 最核心的类，读取用户输入，调用另外两个包的程序
 */
public class AgendaService {
    /**
     * 主函数，读取输入的命令并交给其它类进行处理
     *
     * @param args 命令行获得的参数，但实际并没有用到这个参数
     */
    public static void main(String[] args) {
        //打印提示信息
        printHelp();
        System.out.print("$ ");
        Scanner input = new Scanner(System.in);
        //获取用户输入
        String[] commands = input.nextLine().split(" ");
        //实例化接口
        Command c;
        //新建用户列表
        List<User> users = new ArrayList<>();
        while (!commands[0].equalsIgnoreCase("quit")) {     //判断是否退出程序
            if (commands[0].equalsIgnoreCase("batch")) {    //判断是否要执行文件中但批处理命令
                System.out.print("$ ");
                try {
                    //读取文件中的批处理命令
                    FileReader m;
                    m = new FileReader(commands[1]);
                    BufferedReader reader = new BufferedReader(m);
                    String nextLine;
                    nextLine = reader.readLine();                       //一次读一行
                    while (!(nextLine == null)) {
                        System.out.println(nextLine);
                        commands = nextLine.split(" ");
                        c = switchCommand(commands);                    //根据命令选择功能
                        if (c == null)
                            break;
                        //检查参数个数
                        if (!c.check(commands)) {
                            System.out.println("  参数个数不正确，请输入正确指令参数，输入help以获得提示");
                        }
                        //执行命令
                        c.exec(commands, users);
                        System.out.print("$ ");
                        nextLine = reader.readLine();
                    }
                    commands = input.nextLine().split(" ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                c = switchCommand(commands);                            //根据命令选择功能
                if (c == null)
                    break;
                //检查参数个数
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

    /**
     * 打印相关信息与命令提示
     */
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

    /**
     * 使用多态根据命令选择实例化成什么类型的对象
     *
     * @param commands 输入但命令
     * @return 一个从Command接口实例化出来的对象
     */
    static Command switchCommand(String[] commands) {
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
