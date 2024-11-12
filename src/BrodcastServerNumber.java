import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BrodcastServerNumber {
    /**
     * 服务器连接方式包括直接相连，间接连接。
     * A和B直接连接，B和C直接连接，则A和C间接连接。
     * 直接连接和间接连接都可以发送广播。
     * 给出一个N*N数组，代表N个服务器，
     *
     * matrix[i][j] == 1，
     * 则代表i和j直接连接；不等于 1 时，代表i和j不直接连接。
     *
     * matrix[i][i] == 1，
     * 即自己和自己直接连接。matrix[i][j] == matrix[j][i]。
     *
     * 计算初始需要给几台服务器广播， 才可以使每个服务器都收到广播。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String[] line1Split = line1.split(" ");
        int n = line1Split.length;  // 服务器的数量，n 为矩阵的维度

        // 创建 n*n 的二维数组来存储服务器连接状态
        int[][] arr = new int[n][n];

        // 将第一行的连接状态转换为整数并存入数组 arr
        for(int i = 0; i < n; i++) {
            arr[0][i] = Integer.parseInt(line1Split[i]);
        }

        // 读取剩下的 n-1 行，并逐行存入 arr 矩阵
        for(int i = 1; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        // 记录连通分量的数量（即需要广播的服务器数量）
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if (!queue.contains(i)) {
                dfs(arr, queue, i);
                count++;
            }
        }

        System.out.println(count);
    }

    // 递归查找服务器的所有连通节点
    static void dfs(int[][] arr, Queue<Integer> queue, int index) {
        queue.offer(index);
        for (int j = 0; j < arr.length; j++) {
            if (arr[index][j] == 1 && !queue.contains(j)) {
                dfs(arr, queue, j);
            }
        }
    }
}
