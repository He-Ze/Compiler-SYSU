import java.io.*;
import exceptions.*;

/**
 * 根据命令行参数读入测试用例
 * 并调用词法分析入口程序完成单词扫描过程
 * 再根据返回结果判断是否与预期输出一致
 */
public class Main {
	/**
	 * 主函数
	 *
	 * @param 源文件目录
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			try {
				OberonScanner scanner = new OberonScanner(new java.io.FileReader(args[i]));
				String kindOfWord;
				while (true) {
					try {
						kindOfWord = scanner.yylex();
						System.out.println(kindOfWord + " : " + scanner.yytext());
					} catch (LexicalException e) {
						System.out.print(scanner.yytext() + "处出现错误：");
						System.out.println(e);
						break;
					}
					if (kindOfWord.equals("EOF")) {
						System.out.println("无词法错误");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
