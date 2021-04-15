import java.io.*;
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
    static int length;
    static List<Character> result;
    static List<Character> input;

    public Parser() throws IOException {
        lookahead = System.in.read();
        length=System.in.available();
        result=new ArrayList<>();
        input=new ArrayList<>();
        input.add((char)lookahead);
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
             else if(lookahead==' '){
                System.out.println("--------------------------------");
             	System.out.println(input.get(input.lastIndexOf(' ')-1)+"之后不应该有空格，已自动忽略");
             	System.out.println("--------------------------------");
             	lookahead=System.in.read();
                input.add((char)lookahead);
             	continue;
             }
            length--;
        }
    }

    void term() throws IOException {
        if (Character.isDigit((char) lookahead)) {
            result.add((char) lookahead);
            match(lookahead);
        } else
            throw new Error("syntax error");
    }

    void match(int t) throws IOException {
        if (lookahead == t) {
            lookahead = System.in.read();
            input.add((char)lookahead);
        }
        else
            throw new Error("syntax error");
    }
}
