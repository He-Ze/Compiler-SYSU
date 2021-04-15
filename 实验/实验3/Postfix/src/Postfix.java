import java.io.*;
import java.util.*;

public class Postfix {
    public static void main(String[] args) throws IOException {
        System.out.println("Input an infix expression and output its postfix notation:");
        new Parser().expr();
        System.out.println("\n------------------------------------------");
        System.out.println("End of program.");
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
        System.out.println("------------------------------------------");
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
        while(length>=0){
            if(indexOfLookahead<length && Character.isDigit((char) lookahead)){
                print2Locations(indexOfLookahead);
                System.out.println("这两个运算量间缺少运算符，已自动忽略第二个运算量");
                System.out.println("------------------------------------------");
                indexOfLookahead++;
                lookahead=input.get(indexOfLookahead);
            }
            else if(indexOfLookahead<length && lookahead!='+' && lookahead!='-'){
                printLocation(indexOfLookahead);
                System.out.println("非法运算符，已自动忽略，只支持+与-");
                System.out.println("------------------------------------------");
                indexOfLookahead++;
                lookahead=input.get(indexOfLookahead);
            }
            else if (lookahead == '+') {
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
        while(!Character.isDigit((char) lookahead)){
            printLocation(indexOfLookahead);
            System.out.println("缺少左运算量，已自动忽略此运算符");
            System.out.println("------------------------------------------");
            indexOfLookahead++;
            lookahead=input.get(indexOfLookahead);
        }
        result.add((char) lookahead);
        if(indexOfLookahead<input.size()-1)
            match(lookahead);
    }

    void match(int t) throws IOException {
        if (lookahead == t) {
            indexOfLookahead++;
            lookahead = input.get(indexOfLookahead);
            while(lookahead==' '){
                printLocation(indexOfLookahead);
                System.out.println("此处不应该有空格，已自动忽略");
                System.out.println("------------------------------------------");
                indexOfLookahead++;
                lookahead = input.get(indexOfLookahead);
            }
        } else
            throw new Error("syntax error");
    }
    void printLocation(int index){
        for (Character character : input) {
            System.out.print(character);
        }
        System.out.print('\n');
        for (int i=0;i<index;i++){
            System.out.print(' ');
        }
        System.out.println('^');
    }
    void print2Locations(int index){
        for (Character character : input) {
            System.out.print(character);
        }
        System.out.print('\n');
        for (int i=0;i<index-1;i++){
            System.out.print(' ');
        }
        System.out.println("^^");
    }
}
