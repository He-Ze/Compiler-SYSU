import java.io.*;
import java.util.*;

/**
 * 主类，调用Parser类
 */
public class Postfix {
    /**
     * 程序主函数，生成一个Parser对象
     *
     * @param args 命令行输入参数，但此程序并未用到
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Input an infix expression and output its postfix notation:");
        new Parser().expr();
        System.out.println("\n------------------------------------------");
        System.out.println("End of program.");
    }
}

/**
 * 对输入的中缀表达式进行拆分与分析
 */
class Parser {
    /**
     * 一个存储输入中缀表达式的列表
     */
    static List<Character> input;
    /**
     * 一个存储结果后缀表达式的列表
     */
    static List<Character> result;
    /**
     * 下一个要分析的字符.
     */
    static int lookahead;
    /**
     * 下一个要分析的字符在总的输入表达式中的索引即第几个字符.
     */
    static int indexOfLookahead;
    /**
     * 总的输入表达式的字符长度
     */
    static int length;

    /**
     * 构造函数，获取所有用户输入并存储到输入列表中
     */
    public Parser() {
        result = new ArrayList<>();
        input = new ArrayList<>();
        indexOfLookahead = 0;
        Scanner s = new Scanner(System.in);
        String in = s.nextLine();
        for (int i = 0; i < in.length(); i++) {
            input.add(in.charAt(i));
        }
        length = input.size();
        lookahead = input.get(indexOfLookahead);
        System.out.println("------------------------------------------");
    }

    /**
     * 调用下面两个进行转化的函数并在最后输出结果
     *
     * @throws IOException the io exception
     */
    void expr() throws IOException {
        term();
        rest();
        System.out.print("转换为后缀表达式的结果为：");
        for (Character character : result) {
            System.out.print(character);
        }
    }

    /**
     * 检测当前位置是否为+或-
     * 若是则调用match与term函数读取下一位分析并将此运算符加入结果列表
     * 否则定位并输出错误信息并自动忽略此字符并读取下一个字符直到遇到+或-
     *
     * @throws IOException the io exception
     */
    void rest() throws IOException {
        while (length >= 0) {
            if (indexOfLookahead <= length && Character.isDigit((char) lookahead)) {
                print2Locations(indexOfLookahead);
                System.out.println("这两个运算量间缺少运算符，已自动忽略第二个运算量");
                System.out.println("------------------------------------------");
                if (indexOfLookahead < length)
                    indexOfLookahead++;
                lookahead = input.get(indexOfLookahead);
            } else if (indexOfLookahead <= length && lookahead != '+' && lookahead != '-') {
                printLocation(indexOfLookahead);
                System.out.println("非法运算符，已自动忽略，只支持+与-");
                System.out.println("------------------------------------------");
                if (indexOfLookahead < length)
                    indexOfLookahead++;
                lookahead = input.get(indexOfLookahead);
            } else if (lookahead == '+') {
                match('+');
                term();
                result.add('+');
            } else if (lookahead == '-') {
                match('-');
                term();
                result.add('-');
            }
            length--;
        }
    }

    /**
     * 检测当前位置是否为数字
     * 若是则将此数字加入到结果列表中
     * 否则提示出错并自动忽略此字符并读取下一个字符直到遇到数字
     *
     * @throws IOException the io exception
     */
    void term() throws IOException {
        while (!Character.isDigit((char) lookahead)) {
            printLocation(indexOfLookahead);
            System.out.println("缺少左运算量，已自动忽略此运算符");
            System.out.println("------------------------------------------");
            indexOfLookahead++;
            lookahead = input.get(indexOfLookahead);
        }
        result.add((char) lookahead);
        if (indexOfLookahead < input.size() - 1)
            match(lookahead);
    }

    /**
     * 检测当前lookahead与传入到字符是否一致
     * 若一致则读取下一个字符并检测是否为空格，若是则输出错误信息并读取下一个非空格字符
     * 否则报错
     *
     * @param t the t
     * @throws IOException the io exception
     */
    void match(int t) throws IOException {
        if (lookahead == t) {
            indexOfLookahead++;
            lookahead = input.get(indexOfLookahead);
            while (lookahead == ' ') {
                printLocation(indexOfLookahead);
                System.out.println("此处不应该有空格，已自动忽略");
                System.out.println("------------------------------------------");
                indexOfLookahead++;
                lookahead = input.get(indexOfLookahead);
            }
        } else
            throw new Error("syntax error");
    }

    /**
     * 打印当前lookahead在输入表达式的位置
     * 用于打印错误信息
     *
     * @param index 当前出错字符在输入列表中的索引
     */
    void printLocation(int index) {
        for (Character character : input) {
            System.out.print(character);
        }
        System.out.print('\n');
        for (int i = 0; i < index; i++) {
            System.out.print(' ');
        }
        System.out.println('^');
    }

    /**
     * 用于打印缺少运算符的两个运算量的位置
     *
     * @param index 当前出错字符在输入列表中的索引
     */
    void print2Locations(int index) {
        for (Character character : input) {
            System.out.print(character);
        }
        System.out.print('\n');
        for (int i = 0; i < index - 1; i++) {
            System.out.print(' ');
        }
        System.out.println("^^");
    }
}
