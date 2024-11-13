import java.util.Scanner;

public class LineUpTallAndShort {
    /**
     * 现在有一队小朋友，他们高矮不同，我们以正整数数组表示这一队小朋友的身高，如数组{5,3,1,2,3}。
     * 我们现在希望小朋友排队，以“高”“矮”“高”“矮”顺序排列，每一个“高”位置的小朋友要比相邻的位置高或者相等；每一个“矮”位置的小朋友要比相邻的位置矮或者相等；
     * 要求小朋友们移动的距离和最小，第一个从“高”位开始排，输出最小移动距离即可。
     * 例如，在示范小队{5,3,1,2,3}中，{5, 1, 3, 2, 3}是排序结果。
     * {5, 2, 3, 1, 3} 虽然也满足“高”“矮”“高”“矮”顺序排列，但小朋友们的移动距离大，所以不是最优结果。
     *
     * 移动距离的定义如下所示：
     * 第二位小朋友移到第三位小朋友后面，移动距离为1，若移动到第四位小朋友后面，移动距离为2；
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 使用正则表达式检查输入字符串是否只包含数字和空格
        // 如果字符串中包含非法字符（非数字或空格），则输出"[]"并退出程序
        if (!s.matches("[0-9\\s]+")) {
            System.out.println("[]");
            return;
        }

        String[] s1 = s.split(" ");
        int[] nums = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            nums[i] = Integer.parseInt(s1[i]);
        }

        // 从头开始
        int i = 0;
        int j = 1;

        for (int x = 0; x < nums.length-1; x++,i++,j++) {
            if (nums[i] != nums[j] && nums[i] > nums[j] != (i%2 ==0)) {
                int tmp  = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        for (int idx = 0; idx < nums.length; idx++) {
            System.out.print(nums[idx] + " ");
        }

    }
}
