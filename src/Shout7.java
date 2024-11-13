import java.util.Scanner;

public class Shout7 {
    /**
     * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
     *
     * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1，但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊”过”。
     *
     * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了”过”，当喊到数字K时，可以统计每个人喊”过”的次数。
     *
     * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊”过”的次数，请把它还原成正确的顺序，即数组的第i个元素存储编号i的人喊”过”的次数。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] s1 = s.split(" ");
        int count = 0;
        // 首先创建一个数组存每个人喊过的次数
        int[] times = new int[s1.length];
        int[] res = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            int time = Integer.parseInt(s1[i]);
            if (time > 0) {
                count+=time;
            }
            times[i] = time;
        }
        // 然后从1开始循环到N，检查每个数字是否是7的倍数或者包含数字7
        int idx = 1;
        while (count>0) {
            if (idx%7 == 0 || idx%10 == 7) {
                count--;
            }
            idx++;
        }
        // 如果不是，我们将这个非7数字加入数组
        for (int i = 1; i < idx; i++) {
            if (i%7 == 0 || i%10 == 7) {
                int j = i%times.length - 1;
                res[j]++;
            }
        }

        // 最后输出数组，其中数组的第a个元素存储编号a的人喊过的次数
        for (int i = 0; i < times.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
