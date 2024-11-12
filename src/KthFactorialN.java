import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KthFactorialN {
    /**
     * 给定参数n，从1到n会有n个整数：1,2,3,…,n,这n个数字共有n!种排列。
     *
     * 按大小顺序升序列出所有排列的情况，并一一标记，
     *
     * 当n=3时,所有排列如下:
     *
     * “123” “132” “213” “231” “312” “321”
     *
     * 给定n和k，返回第k个排列。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getPermutation(n, k));
    }

    /**
     * 康托展开：根据 num -> k
     *     k = a(n) * (n-1)! + a(n-1) * (n-2)! + ... + a(i) * (i-1)! + ... + a(1) * 0!
     * 逆向，即可通过 k 获取 num
     */
    static String getPermutation(int n, int k) {
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        List<Integer> toN = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            toN.add(i);
        }

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }

        // 调整 k 的索引，从 0 开始计算
        k--;
        StringBuilder res = new StringBuilder();

        // 从第一位到最后一位依次计算
        for (int i = n; i > 0; i--) {
            // 计算当前数字索引
            int index = k/factorial[i-1];
            res.append(toN.get(index));
            toN.remove(index);
            k %= factorial[i-1];
        }
        return res.toString();
    }
}
