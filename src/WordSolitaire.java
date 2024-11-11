import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSolitaire {
    /**
     * 单词接龙的规则是：
     *
     * 可用于接龙的单词首字母必须要前一个单词的尾字母相同；
     *
     * 当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
     *
     * 现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，
     *
     * 请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        sc.nextLine();
        int numOfWords = sc.nextInt();
        sc.nextLine();
        List<String> words = new ArrayList<>();
        for(int i = 0; i < numOfWords; i++){
            words.add(sc.nextLine());
        }
        String startWord = words.get(start);
        char suffix = startWord.toCharArray()[startWord.length()-1];
        words.remove(startWord);
        StringBuilder res = new StringBuilder(startWord);
        String nextWord = getNextWord(words, suffix);
        while (nextWord != null ) {
            res.append(nextWord);
            words.remove(nextWord);
            suffix = nextWord.toCharArray()[nextWord.length()-1];
            nextWord  = getNextWord(words, suffix);
        }
        System.out.println(res);

    }

    static String getNextWord(List<String> words, char suffix){
        List<String> alt = new ArrayList<String>();
        int maxLen = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(0) == suffix) {
                alt.add(words.get(i));
                maxLen = Math.max(maxLen, words.get(i).length());
            }
        }
        if (alt.isEmpty()) {
            return null;
        }
        int finalMaxLen = maxLen;
        List<String> resList = alt.stream()
                .filter(s -> s.length() == finalMaxLen)
                .sorted()
                .toList();
        if (resList.size() > 1 ) {
            for (int i = 1; i < resList.size(); i++) {
                words.remove(resList.get(i));
            }
        }
        return resList.get(0);
    }
}
