import java.util.Scanner;

public class Inequality {
    /**
     * 首先解析输入数据，然后计算每个不等式的差值，并判断不等式是否成立。
     * 最后，程序将输出不等式是否成立（true或false）以及不等式的最大差值的整数部分。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(";");

        // 读取不等式系数
        double[][] coefficients = new double[3][5];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                String[] cTmp = line[i].split(",");
                coefficients[i][j] = Double.parseDouble(cTmp[j]);
            }
        }

        // 读取不等式变量
        int[] variables = new int[5];
        String[] vTmp = line[3].split(",");
        for (int i = 0; i < 5; i++) {
            variables[i] = Integer.parseInt(vTmp[i]);
        }

        // 读取不等式目标值
        double[] targets = new double[3];
        String[] tTmp = line[4].split(",");
        for (int i = 0; i < 3; i++) {
            targets[i] = Double.parseDouble(tTmp[i]);
        }

        // 读取不等式约束
        String[] constraints = line[5].split(",");
        double res = 0;
        boolean flag = true;

        // 分别计算每个不等式的差值，并判断不等式是否成立
        for (int i = 0; i < 3; i++) {
            double diff = calculateDifference(coefficients[i], variables, targets[i]);
            if (! checkConstraint(diff, constraints[i])) {
                flag = false;
            }
            res = Math.max(res, diff);
        }
        System.out.println(flag + " " + (int)res);

    }


    private static double calculateDifference(double[] coefficients, int[] variables, double target) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * variables[i];
        }
        return sum - target;
    }

    private static boolean checkConstraint(double difference, String constraint) {
        return switch (constraint) {
            case "<=" -> difference <= 0;
            case ">=" -> difference >= 0;
            case "<" -> difference < 0;
            case ">" -> difference > 0;
            case "=" -> difference == 0;
            default -> throw new IllegalArgumentException("Invalid constraint: " + constraint);
        };
    }
}
