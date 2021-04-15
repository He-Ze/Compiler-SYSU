import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Postfix {
    public static void main(String[] args) throws IOException {
        System.out.println("Input an infix expression and output its postfix notation:");
        new Parser().expr();
        System.out.println("\nEnd of program.");
    }
}

class Parser {
    static int lookahead;
    static int indexOfLookahead;
    static int length;
    static List<Character> result;
    static List<Character> input;

    public Parser() throws IOException {
        result=new ArrayList<>();
        input=new ArrayList<>();
        indexOfLookahead=0;
        Scanner s=new Scanner(System.in);
        String in=s.nextLine();
        for(int i=0;i<in.length();i++){
            input.add(in.charAt(i));
        }
        length=input.size();
        lookahead = input.get(indexOfLookahead);
    }

    void expr() throws IOException {
        term();
        rest();
        System.out.print("转换为后缀表达式的结果为：");
        for (Character character : result) {
            System.out.print(character);
        }
    }

    void rest() throws IOException {
        while(length>0){
            if (lookahead == '+') {
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

    void term() throws IOException {
        if (Character.isDigit((char) lookahead)) {
            result.add((char) lookahead);
            if(indexOfLookahead<length-1)
                match(lookahead);
        } else
            throw new Error("syntax error");
    }

    void match(int t) throws IOException {
        if (lookahead == t) {
            indexOfLookahead++;
            lookahead = input.get(indexOfLookahead);
            if(lookahead==' '){
                System.out.println("--------------------------------");
                printLocation(lookahead);
                System.out.println("此处不应该有空格，已自动忽略");
                System.out.println("--------------------------------");
                indexOfLookahead++;
                lookahead = input.get(indexOfLookahead);
            }
        }
        else
            throw new Error("syntax error");
    }
    void printLocation(int t){
        int index=input.lastIndexOf((char)t);
        for (Character character : input) {
            System.out.print(character);
        }
        System.out.print('\n');
        for (int i=0;i<index;i++){
            System.out.print(' ');
        }
        System.out.println('^');
    }
}
