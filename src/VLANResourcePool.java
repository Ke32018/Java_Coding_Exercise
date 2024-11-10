import java.util.*;

public class VLANResourcePool {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String VLANPool = sc.nextLine();
        String target = sc.nextLine();

        String[] VLANs = VLANPool.split(",");
        List<String> waitingSortedVlan = new ArrayList<>();
        for (int i = 0; i < VLANs.length; i++) {
            if (isInPool(target, VLANs[i])) {
                VLANs[i] = getVlan(VLANs[i], target);
            }
            if (! VLANs[i].isEmpty()) {
                waitingSortedVlan.add(VLANs[i]);
            }
        }

        waitingSortedVlan.sort(new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = Integer.parseInt(o1.split("-")[0]);
                int num2 = Integer.parseInt(o2.split("-")[0]);
                return Integer.compare(num1, num2);
            }
        });
        String res = String.join(",",  waitingSortedVlan);
        System.out.print(res);

    }
    public static boolean isInPool(String VLAN, String vlanPool) {
        if (VLAN.equals(vlanPool)) {
            return true;
        }   
        String[] VLANs = vlanPool.split("-");
        if (VLANs.length == 1) {
            return false;
        }
        int start = Integer.parseInt(VLANs[0]);
        int end = Integer.parseInt(VLANs[1]);
        if (Integer.parseInt(VLAN) > start && Integer.parseInt(VLAN) < end) {
            return true;
        }
        return false;
    }

    public static String getVlan(String part, String target) {
        if (part.equals(target)) {
            return "";
        }
        String[] vlans = part.split("-");
        int init = Integer.parseInt(vlans[0]);
        int end = Integer.parseInt(vlans[1]);
        int flag =init;
        int length = end - init + 1;
        String[] longVlans = new String[length];
        String part1 = "";
        String part2 = "";
        int targetInt = Integer.parseInt(target);
        for (int i = 0; i < length; i++) {
            if (flag != targetInt) {
                longVlans[i] = String.valueOf(flag);
            } else {
                if (flag != init + 1) {
                    part1 = init + "-" + (targetInt - 1);
                } else {
                    part1 = String.valueOf(init);
                }
                if (flag != end - 1) {
                    part2 = targetInt + 1 + "-" + end;
                } else {
                    part2 = String.valueOf(end);
                }
            }
            flag++;
        }
        return part1 + "," + part2;
    }
}

