import java.io.*;

import javax.swing.plaf.synth.ColorType;

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

	public Parser() throws IOException {
		lookahead = System.in.read();
		length=System.in.available();
	}

	void expr() throws IOException {
		term();
		rest();
	}

	void rest() throws IOException {
		while(length>0){
			if (lookahead == '+') {
				match('+');
				term();
				System.out.write('+');
			} else if (lookahead == '-') {
				match('-');
				term();
				System.out.write('-');
			} 

			length--;
		}
	}

	void term() throws IOException {
		if (Character.isDigit((char) lookahead)) {
			System.out.write((char) lookahead);
			match(lookahead);
		} else
			throw new Error("syntax error");
	}

	void match(int t) throws IOException {
		if (lookahead == t)
			lookahead = System.in.read();
		else
			throw new Error("syntax error");
	}
}
