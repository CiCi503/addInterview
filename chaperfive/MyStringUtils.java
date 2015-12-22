package chaperfive;

import java.util.Arrays;

public class MyStringUtils {
    /**
     * 判断两个字符串是否为变形词
     */
    /*
     * 方法1：将字符串转换成数组，若是变形词，那么排序后的两个数组元素顺序是一致的
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
    
}
