import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Scanner;

public class AveragePixelValue {
    /**
     * 题目描述
     * 一个图像有n个像素点，存储在一个长度为n的数组img里，每个像素点的取值范围[0,255]的正整数。
     *
     * 请你给图像每个像素点值加上一个整数k（可以是负数），得到新图newImg，使得新图newImg的所有像素平均值最接近中位值128。
     *
     * 请输出这个整数k。
     *
     * 输入描述
     * n个整数，中间用空格分开
     *
     * 备注
     * • 1 <= n <= 100
     * • 如有多个整数k都满足，输出小的那个k；
     * • 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
     *
     * 例如newImg=”-1 -2 256″,会自动更改为”0 0 255″
     *
     * 输出描述
     * 一个整数k
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nums = sc.nextLine().split(" ");
        // 处理像素值
        int[] imgPixels = new int[nums.length];
        int[] newImgPixels = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            imgPixels[i] = Integer.parseInt(nums[i]);
        }
        // 对于新数组 newImg 中的每个像素值，如果超过255，就设为255；小于0则设为0.
        // 寻找最优K值：遍历可能的K值，计算每个K值下的平均值，找到使平均值最接近128的K值
        int k = 0;
        double diff = Double.MAX_VALUE;
        for (int i = -128; i <= 128; i++) {
            for (int j = 0; j < imgPixels.length; j++) {
                newImgPixels[j] = imgPixels[j] + i;
                if (newImgPixels[j] > 255) {newImgPixels[j] = 255;}
                if (newImgPixels[j] < 0) {newImgPixels[j] = 0;}
            }
            double v = averagePixelValue(newImgPixels);
            double tmpDiff = Math.abs(v - (double) 128);
            if (tmpDiff < diff) {
                diff = tmpDiff;
                k = i;
            }
        }

        // 输出
        System.out.println(k);
    }

    static double averagePixelValue(int[] imgPixels) {
        OptionalDouble average = Arrays.stream(imgPixels).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
