import java.util.*;

public class TVLDecoding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入待解码信元的Tag
        String tag = sc.nextLine();
        // 输入16进制码流
        String line = sc.nextLine();

        // 将16进制码流按空格分割成字符串数组
        String[] hexArray = line.split(" ");
        // 从字符串开头解析，直到获取到相应信元的 Tag

        System.out.println(decoding(tag, hexArray));

    }

    public static String decoding(String targetTag, String[] hexArray) {
        String tag = hexArray[0];
        int length = Integer.parseInt(hexArray[2] + hexArray[1], 16);

        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < length + 3; i++) {
            sb.append(hexArray[i]);
            sb.append(" ");
        }
        String res = sb.toString().trim();
        if (!tag.equals(targetTag)) {
            String[] next = new String[hexArray.length - length - 3];
            for (int i = length + 3; i < hexArray.length; i++) {
                next[i - length - 3] = hexArray[i];
            }
            res = decoding(targetTag, next);
        }
        return res;
    }
}
