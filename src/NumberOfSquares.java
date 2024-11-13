import java.util.ArrayList;
import java.util.Scanner;

public class NumberOfSquares {
    /**
     * 输入N个互不相同的二维整数坐标，求这N个坐标可以构成的正方形数量。[内积为零的的两个向量垂直]
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Point> points = new ArrayList<>();

        // 读取所有点的坐标
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine();
            points.add(new Point(x, y));
        }

        int squareCount = 0;

        // 遍历所有点对，检查是否能构成正方形
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                if (p1.equals(p2)) {continue;}
                // 计算两个可能的对角点
                Point p3 = new Point(p1.x - (p1.y - p2.y), p1.y + (p1.x - p2.x));
                Point p4 = new Point(p2.x - (p1.y - p2.y), p2.y + (p1.x - p2.x));

                if (pointExists(points, p3) && pointExists(points, p4)) {
                    squareCount++;
                }

                // 计算另外两个可能的对角点
                Point p5 = new Point(p1.x + (p1.y - p2.y), p1.y - (p1.x - p2.x));
                Point p6 = new Point(p2.x + (p1.y - p2.y), p2.y - (p1.x - p2.x));

                if (pointExists(points, p5) && pointExists(points, p6)) {
                    squareCount++;
                }
            }
        }
        // 每个正方形被计算了4次，因此结果需要除以4
        System.out.println(squareCount / 4);

        scanner.close();

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 判断两个点是否相等
        boolean equals(Point p) {
            return this.x == p.x && this.y == p.y;
        }
    }
    // 检查点是否存在于点列表中
    static boolean pointExists(ArrayList<Point> points, Point p) {
        for (Point point : points) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }
}
