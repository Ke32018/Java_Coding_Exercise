import java.util.*;

/**
 * 题目描述
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植N棵胡杨（编号1-N），排成一排。
 * 一个月后，有M棵胡杨未能成活
 * 现可补种胡杨K棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 * 输入描述
 * N 总种植数量，1 <= N <= 100000
 * M 未成活胡杨数量，M 个空格分隔的数，按编号从小到大排列，1 <= M <= N
 * K 最多可以补种的数量，0 <= K <= M
 * 输出描述
 * 最多的连续胡杨棵树
 */
public class ReplantingTrees {
    public static void main(String[] args) {
        // 创建一个扫描器对象，用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 读取总共的胡杨树数量
        int total = scanner.nextInt();

        // 读取未成活的胡杨树数量
        int deadCount = scanner.nextInt();

        // 创建一个数组来表示每棵树是否成活，0表示成活，1表示未成活
        int[] nums = new int[total];

        // 初始化数组，所有元素设为0，表示所有树最初都是成活的
        Arrays.fill(nums, 0);

        // 根据输入，将未成活的树的位置标记为1
        for (int i = 0; i < deadCount; i++) {
            int num = scanner.nextInt();
            nums[num - 1] = 1; // 树的编号从1开始，因此需要减1
        }

        // 读取可以补种的树的数量
        int supplementCount = scanner.nextInt();

        // 使用双指针/滑动窗口
        int left = 0;
        int sumRight = 0;
        int sumLeft = 0;
        int res = 0;

        for (int right = 0; right < total; right++) {
            sumRight += nums[right];
            // 控制窗口内最大可以补种的数量
            while (sumRight - sumLeft > supplementCount) {
                sumLeft += nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        System.out.println(res);
    }
}
