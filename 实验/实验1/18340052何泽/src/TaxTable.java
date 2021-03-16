package personaltax;

/**
 * 根据计税规则维护一个表，包括创建和修改
 */
public class TaxTable {
    private int taxThreshold;
    private int rankNum;
    private int[] separation;
    private double[] taxRate;

    /**
     * 构造函数，默认起征点1600元
     * 默认超过起征点0-500的部分税率5%，500-2000的税率10%
     * 2000-5000税率15% ，5000-20000税率20%，超过20000的税率25%
     */
    public TaxTable() {
        taxThreshold = 1600;
        rankNum = 5;
        separation = new int[]{0, 500, 2000, 5000, 20000};
        taxRate = new double[]{0.05, 0.1, 0.15, 0.2, 0.25};
    }

    /**
     * 获取起征点金额
     * @return 起征点金额
     */
    public int getTax_Threshold() {
        return taxThreshold;
    }

    /**
     * 获取一共分几级
     * @return 临界点的个数
     */
    public int getRank_Num() {
        return rankNum;
    }

    /**
     * 获取所有临界点金额
     * @return 各临界点金额组成的数组
     */
    public int[] getSeparations() {
        return separation;
    }

    /**
     * 获取某个临界点金额
     * @param i 想获得第i个临界点金额
     * @return 第i个临界点金额
     */
    public int getSeparation(int i) {
        return separation[i];
    }

    /**
     * 获取各级税率
     * @return 各级税率组成的数组
     */
    public double[] getTax_Rates() {
        return taxRate;
    }

    /**
     * 获取某级税率
     * @param i 想获得第i级税率
     * @return 第i级税率
     */
    public double getTax_Rate(int i) {
        return taxRate[i];
    }

    /**
     * 设置分的级数
     * @param newRankNum 新的等级数量
     */
    public void setRankNum(int newRankNum) {
        rankNum = newRankNum;
    }

    /**
     * 设置新的起征点金额，并保证金额不小于0
     * @param newThreshold 新的起征点金额
     */
    public void setTax_Threshold(int newThreshold) {
        if (newThreshold < 0) {
            System.out.println("-------------------------------");
            System.out.println("修改失败，请输入大于0的起征点金额");
            System.out.println("-------------------------------");
            return;
        }
        taxThreshold = newThreshold;
    }

    /**
     * 设置新的临界点，并保证金额数均不小于0
     * @param newSeparation 新的临界点组成的数组
     */
    public void setSeparation(int[] newSeparation) {
        for (int j : newSeparation) {
            if (j < 0) {
                System.out.println("-------------------------------");
                System.out.println("修改失败，分隔金额必须不小于0");
                System.out.println("-------------------------------");
                return;
            }
        }
        separation = newSeparation;
    }

    /**
     * 设置新的税率，并保证税率均合法（在0、1之间）
     * @param newRate 新的税率组成的数组
     */
    public void setTaxRate(double[] newRate) {
        for (double v : newRate) {
            if (v < 0 || v > 1) {
                System.out.println("-------------------------------");
                System.out.println("修改失败，税率必须在0和1之间");
                System.out.println("-------------------------------");
                return;
            }
        }
        taxRate = newRate;
    }
}
