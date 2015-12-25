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
     * 给定一个字符串str,求其中全部数字字串所代表的数字只和
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
        int num = 0;// 数字子串表示的数值
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
    
}
