import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongestContinuousSignal {
    /**
     * 输入一串方波信号，求取最长的完全连续交替方波信号，并将其输出，如果有相同长度的交替方波信号，输出任一即可。方波信号高位用1标识，低位用0标识 。
     *
     * 说明：
     *
     * 一个完整的信号一定以0开始然后以0结尾，即010是一个完整信号，但101，1010，0101不是
     * 输入的一串方波信号是由一个或多个完整信号组成
     * 两个相邻信号之间可能有0个或多个低位，如0110010，011000010
     * 同一个信号中可以有连续的高位，如01110101011110001010，前14位是一个具有连续高位的信号
     * 完全连续交替方波是指10交替，如01010是完全连续交替方波，0110不是
     * 输入
     * 输入信号字符串（长度 >= 3 且 <= 1024）：
     *
     * 例如：0010101010110000101000010
     *
     * 注：输入总是合法的，不用考虑异常情况
     *
     * 输出
     * 输出最长的完全连续交替方波信号串
     *
     * 例如：01010
     *
     * 若不存在完全连续交替方波信号串，输出 -1。
     */
    public static void main(String[] args) {
        // 一串方波信号由一个或多个完整信号组成
        // 处理输入，用双指针法完成最长完全连续交替方波信号串的搜索。
        // 初始化变量存储找到的最长序列的长度
        // 遍历输入的字符串，寻找满足条件的序列，
        // 每找到一个满足条件的序列时更新最长序列长度
        // 遍历结束后没找到符合条件的序列组输出-1
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        char[] chars = line.toCharArray();
        Pattern pattern = Pattern.compile("^(01)+0$");
        StringBuilder sb = new StringBuilder();
        String res = "-1";
        int MaxLength = 0;
        for (char c: chars) {
            if (c == '0' && !sb.isEmpty() && sb.charAt(sb.length() - 1) == '0') {
                Matcher matcher = pattern.matcher(sb);
                if (matcher.find() && sb.length() > MaxLength) {
                    res = sb.toString();
                    MaxLength = sb.length();
                }
                sb.setLength(0);
            }
            sb.append(c);
        }
        Matcher matcher = pattern.matcher(sb.toString()); // 对最后一个信号进行匹配
        if (matcher.find() && sb.length() > res.length()) { // 如果匹配到完全连续交替方波信号，并且长度大于之前的最大长度
            res = sb.toString(); // 更新最大长度对应的字符串
        }
        System.out.println(res);
    }

}
