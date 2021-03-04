package personaltax;

/**
 * 这个类用于计算所得税的具体金额
 */
public class TaxCalculator {
    /**
     * 计算所得税的具体金额的方法
     *
     * @param income   收入的金额
     * @param taxtable 存储计算税金额规则的表
     * @return 个人所得税的金额
     */
    public double taxCalculate(int income, TaxTable taxtable) {
        if (income < 0) {
            System.out.println("计算失败，工资金额必须不小于0");
            return 0;
        }
        double tax = 0;
        int rank;
        if (income < taxtable.getTax_Threshold()) {
            return 0;
        }
        int _income = income - taxtable.getTax_Threshold();
        for (rank = 1; rank < taxtable.getRank_Num() && _income > taxtable.getSeparation(rank); rank++) {
            tax += (taxtable.getSeparation(rank) - taxtable.getSeparation(rank - 1)) * taxtable.getTax_Rate(rank - 1);
        }
        tax += (_income - taxtable.getSeparation(rank - 1)) * taxtable.getTax_Rate(rank - 1);
        return tax;
    }
}
