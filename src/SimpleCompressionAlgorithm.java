import java.util.Scanner;

public class SimpleCompressionAlgorithm {
    /**
     * 题目描述
     * 有一种简易压缩算法：针对全部为小写英文字母组成的字符串， 将其中连续超过两个相同字母的部分压缩为连续个数加该字母 其他部分保持原样不变.
     *
     * 例如字符串aaabbccccd 经过压缩变成字符串 3abb4cd
     *
     * 请您编写解压函数,根据输入的字符串,判断其是否为合法压缩过的字符串
     *
     * 若输入合法则输出解压缩后的字符串
     * 否则输出字符串!error来报告错误
     * 输入描述
     * 输入一行，为一个 ASCII 字符串
     *
     * 长度不超过100字符
     *
     * 用例保证输出的字符串长度也不会超过100字符串
     *
     * 输出描述
     * 若判断输入为合法的经过压缩后的字符串
     *
     * 则输出压缩前的字符串
     *
     * 若输入不合法 则输出字符串!error
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        // 理解压缩规则：连续超过两个相同的英文字母会被压缩为 连续个数 + 小写英文字母
        // 编写解压缩函数：函数需要遍历输入的字符串，根据压缩规则进行解压缩
        System.out.println(decompress(s));

        // 检查输入合法性：解压缩过程中检查字符串是否符合压缩规则，不符合则记录错误，并输出相应错误信息。
        // 输出结果
    }

    static String decompress(String compressed) {
        StringBuilder sb = new StringBuilder();
        char[] chars = compressed.toCharArray();
        int idx = 0;

        while (idx < chars.length - 2) {
            char c = chars[idx];
            if (c >= 'a' && c <= 'z') {
                if (chars[idx + 2] == chars[idx + 1]) {
                    return "!error";
                }
                if (chars[idx + 1] != c) {
                    sb.append(c);
                    idx++;
                } else {
                    sb.append(c).append(c);
                    idx += 2;
                }
            } else {
                if (chars[idx + 2] == chars[idx + 1]) {
                    return "!error";
                }
                int accNum = c - '0';
                if (accNum <= 2) {
                    return "!error";
                }
                for (int i = 0; i < accNum; i++) {
                    sb.append(chars[idx+1]);
                }
                idx += 2;
            }
        }
        if (idx >= chars.length) {
            return sb.toString();
        } else if (idx == chars.length - 1) {
            sb.append(chars[idx]);
        } else {
            if (chars[idx] >= 'a' && chars[idx] <= 'z') {
                sb.append(chars[idx]).append(chars[idx + 1]);
            } else {
                int accNum = chars[idx] - '0';
                for (int i = 0; i < accNum; i++) {
                    sb.append(chars[idx + 1]);
                }
            }
        }

        return sb.toString();

    }
}
