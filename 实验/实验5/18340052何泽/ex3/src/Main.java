import java.io.*;
import exceptions.*;

/**
 * 主程序
 *
 */
public class Main {
	/**
     * 主函数
	 * @param 源文件
	 */
	public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            OberonScanner scanner = null;
            try {
                scanner = new OberonScanner(new java.io.FileReader(args[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Parser p = new Parser(scanner);
            System.out.print("程序"+args[i]+"的");
            try {	
                p.parse();
            }
            catch(Exception e){
                System.out.print("第"+scanner.get_line()+"行"+ scanner.get_column()+"列出现错误：");
                System.out.println(e+"\n");
            }
        }

	}

}
