package chaperfive;

import java.util.Arrays;

public class MyStringUtils {
    /**
     * 判断两个字符串是否为变形词
     */
    /*
     * 方法1：将字符串转换成数组，若是变形词，那么排序后的两个数组元素顺序是一致的
     * 这种做法效率较低，但是比较直观
     */
    public static boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        // 或者将数组重新转为字符串就行判断
//        return new String(arr1).equals(new String(arr2));
        return true;
    }
    /*
     * 方法2：假设字符串中字符都是ASCII字符，那么使用一个长度为256的数组来记录每个字符在字符串中出现的长度
     */
    public static boolean isDeformation2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        int[] charCount = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            charCount[str1.charAt(i)]++;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (--charCount[str1.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 给定一个字符串str,求其中全部数字字串所代表的数字之和
     * 
     * 要求：忽略小数点,如“A1.3”，包含1和3
     * 如果贴紧数字字串的左侧出现-号，当连续出现的数量为奇数时，则数字为负，否则为正，
     * 不连续出现的-号不作数。
     * 例如：“A-1BC--12”，包含数字为-1和12
     * 
     */
    public static int numSumInStr(String str) {
        if (str == null) {
            return 0;
        }
        boolean sign = true;// 表示num的正负
        int cur = 0;
        int sum = 0;
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            cur = str.charAt(i) - '0';
            if (cur < 0 || cur > 9) {
                sum += num;// 在非数字时进行加和
                num = 0;// num归0
                if (str.charAt(i) == '-') {
                    if (i - 1 > -1 && str.charAt(i - 1) == '-') {// 出现第二个-号
                        sign = !sign;
                    } else {// 只出现了一个-号，负数
                        sign = false;
                    }
                } else {// 没有出现-号，正数
                    sign = true;
                }
            } else {
                num = num * 10 + (sign ? cur : -cur);
            }
        }
        sum += num;// 做最后一次加和（字符串以数字结尾的情况）
        return sum;
    }
    /**
     * 给定一个字符串str和一个整数k,如果str中正好有连续的k个'0'字符出现时，把k个连续的'0'去掉，返回处理后的字符串。
     * 比如：
     * str = "A00B"，k = 2，返回"A**B"；
     * str = "A0000B000"，k = 3，返回"A0000B***";
     */
    public static String delKzeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int zeroStartIndex = -1;
        int zeroCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                zeroCount++;
                zeroStartIndex = (zeroStartIndex == -1 ? i : zeroStartIndex);
            } else {
             // 攒够了连续的k个0，且下一个字符不为0，满足条件，那么消灭这些连续0
                if (zeroCount == k) {
                    while (zeroCount-- > 0) {
                        chars[zeroStartIndex++] = '*';
                    }
                }
                zeroCount = 0;
                zeroStartIndex = -1;// zeroStartIndex重置
            }
        }
        // 由于在满足条件的连续0的下一个字符处做处理，那么如果末尾有满足条件的连续0，
        // 就不会做处理，因此要判断是否满足这种情况（跟上一个题一样）
        if (zeroCount == k) {
            while (zeroCount-- > 0) {
                chars[zeroStartIndex++] = '*';
            }
        }
        return String.valueOf(chars);
    }
    /*
     * 在上面的基础上改良，将*号抹去
     * 比如：
     * str = "A00B"，k = 2，返回"AB"；
     * str = "A0000B000"，k = 3，返回"A0000B";
     * str = "A00000B00"，k = 2，返回"A0000B";
     */
    public static String delKzerosPro(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int zeroStartIndex = -1;
        int zeroCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                zeroCount++;
                zeroStartIndex = (zeroStartIndex == -1 ? i : zeroStartIndex);
            } else {
                // 攒够了连续的k个0，且下一个字符不为0，满足条件，那么消灭这些连续0
                if (zeroCount == k) {
                    int j = zeroStartIndex;
                    for (; j + k < chars.length; j++) {
                        chars[j] = chars[j + k]; 
                     // 原来的位置设置为字符*，为了防止经过前移，恰好形成符合条件的连续0
                        chars[j + k] = '*';
                    }
                    while (j < i) {// 放置后面的不能把前面的完全覆盖
                        chars[j++] = '*';
                    }
                    i = i - k;// 压缩后的索引值
                }
                zeroCount = 0;
                zeroStartIndex = -1;// zeroStartIndex重置
            }
        }
        if (zeroCount == k) {
            while (zeroCount-- > 0) {
                chars[zeroStartIndex++] = '*';
            }
        }
        // 统计有多少个正常字符
        int resultLen = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*') {
                resultLen = i;
                break;
            }
        }
        return new String(chars, 0, resultLen);
        
    }
}
