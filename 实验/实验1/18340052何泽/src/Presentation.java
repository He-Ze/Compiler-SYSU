/*
 *编译原理实验课程作业1
 * 2021年3月2日
 * 何泽  18340052
 * heze_heze@icloud.com
 */
package personaltax;

import java.lang.*;
import java.util.Scanner;

/**
 * 表示层，用于和用户交互和IO
 * @author heze
 * @version v1.0
 */
public class Presentation {
    /**
     * main函数，展示欢迎界面与默认计税规则
     * 并显示和输入的代号与对应功能
     * @param args 命令行输入得到的参数，并没有用到
     */
    public static void main(String[] args) {
        System.out.println("欢迎使用自动计算个人所得税程序");
        TaxTable taxtable = new TaxTable();             //创建计税规则表的对象实例
        System.out.println("-------------------------------");
        printTable(taxtable);                           //打印默认计税规则
        System.out.println("-------------------------------");
        funcSwitch(getInput(), taxtable);               //获取输入，选择对应功能
    }

    /**
     * 打印当前计税规则.
     * @param taxtable 存储各级计税规则的表
     */
    static void printTable(TaxTable taxtable) {
        int rank = taxtable.getRank_Num();              //共有多少个临界点
        int[] separation = taxtable.getSeparations();
        double[] taxRate = taxtable.getTax_Rates();
        System.out.printf("当前起征点为%d元\n", taxtable.getTax_Threshold());
        System.out.println("超过的部分：");
        for (int i = 0; i < rank - 1; i++) {
            System.out.printf("超过%5d元至%5d元区间税率为%3d%%\n", separation[i], separation[i + 1], (int) (taxRate[i] * 100));
        }
        System.out.printf("超过%5d元的税率为%d%%\n", separation[rank - 1], (int) (taxRate[rank - 1] * 100));
    }

    /**
     * 显示可输入的数字并等待用户输入
     * @return 用户输入的数字
     */
    static int getInput() {
        System.out.println("输入以下指令以实现相关功能：");
        System.out.println("1：计算个人所得税\n2：改变起征点\n3：改变税率\n4：退出程序");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入指令代号： ");
        return input.nextInt();
    }

    /**
     * 根据用户输入的数字选择相对应的功能并调用相关函数
     * @param funcNumber 用户输入的数字
     * @param taxtable   存储各级计税规则的表
     */
    static void funcSwitch(int funcNumber, TaxTable taxtable) {
        Scanner input = new Scanner(System.in);
        while (funcNumber != 4) {
            switch (funcNumber) {
                case 1:
                    /* 进入计算所得税的程序 */
                    System.out.println("-------------------------------");
                    System.out.println("正在使用个人所得税计算程序\n请输入您的收入：");
                    TaxCalculator taxCalculator = new TaxCalculator();
                    double tax = taxCalculator.taxCalculate(input.nextInt(), taxtable);
                    System.out.println("您的个人所得税为 " + tax + "元");
                    System.out.println("-------------------------------");
                    break;
                case 2:
                    /*更改起征点*/
                    System.out.println("-------------------------------");
                    System.out.println("目前的起征点为：" + taxtable.getTax_Threshold() + "元，请输入新的起征点：");
                    taxtable.setTax_Threshold(input.nextInt());
                    System.out.print("更改完成，");
                    printTable(taxtable);
                    System.out.println("-------------------------------");
                    break;
                case 3:
                    /*更改计税规则*/
                    System.out.println("-------------------------------");
                    System.out.println("请输入分隔各级的金额：（第一个数字需要为0，各数字以空格分开，回车结束）");
                    String[] inputArray = input.nextLine().split(" ");
                    while (!inputArray[0].equals("0")) {
                        System.out.println("第一个数字必须为0，请重新输入");
                        inputArray = input.nextLine().split(" ");
                    }
                    int[] newSeparation = new int[inputArray.length];
                    for (int i = 0; i < inputArray.length; i++) {
                        newSeparation[i] = Integer.parseInt(inputArray[i]);
                    }
                    taxtable.setRankNum(inputArray.length);
                    taxtable.setSeparation(newSeparation);

                    System.out.println("请输入各级的税率的小数形式：（各数字以空格分开，回车结束）");
                    inputArray = input.nextLine().split(" ");
                    double[] newTaxRate = new double[inputArray.length];
                    for (int i = 0; i < inputArray.length; i++) {
                        newTaxRate[i] = Double.parseDouble(inputArray[i]);
                    }
                    taxtable.setTaxRate(newTaxRate);
                    System.out.print("更改完成，");
                    printTable(taxtable);
                    System.out.println("-------------------------------");
                    break;
                default:
                    System.out.println("-------------------------------");
                    System.out.println("请输入1、2、3、4这四个数字");
                    System.out.println("-------------------------------");
            }
            funcNumber = getInput();
        }
    }
}
