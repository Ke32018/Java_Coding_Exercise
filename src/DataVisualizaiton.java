import java.util.*;

public class DataVisualizaiton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String[] order = scanner.nextLine().split(" ");
        String orderBy = order[0];
        String orderType = order[1];

        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = scanner.nextLine().split(" ");
            String name = input[0];
            int value = Integer.parseInt(input[1]);
            dataList.add(new Data(name, value));
        }

        // Sorting the data
        dataList.sort("Name".equals(orderBy) ? Comparator.comparing(Data::getName) :
                Comparator.comparing(Data::getValue));
        if ("DESC".equals(orderType)) {
            Collections.reverse(dataList);
        }

        // Finding max value and max name length
        int maxValue = dataList.stream().mapToInt(Data::getValue).max().getAsInt();
        int maxNameLength = dataList.stream().mapToInt(d -> d.getName().length()).max().getAsInt();

        // Printing the bar chart
        System.out.println("\u250c" + "\u2500".repeat(maxNameLength + 2) + "\u252c" + "\u2500".repeat(20) + "\u2510");
        for (Data data : dataList) {
            String name = data.getName();
            int value = data.getValue();
            System.out.print("\u2502" + String.format("%" + (maxNameLength + 2) + "s", name) + "\u2502");
            System.out.print("\u2588".repeat((int) ((double) value / maxValue * 20)));
            System.out.println(" ".repeat(20 - (int) ((double) value / maxValue * 20)) + "\u2502");

            if (data != dataList.get(dataList.size() - 1)) {
                System.out.println("\u251c" + "\u2500".repeat(maxNameLength + 2) + "\u253c" + "\u2500".repeat(20) + "\u2524");
            }
        }
        System.out.println("\u2514" + "\u2500".repeat(maxNameLength + 2) + "\u2534" + "\u2500".repeat(20) + "\u2518");
    }


    static class Data {
        String name;
        int value;

        public Data(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }}
