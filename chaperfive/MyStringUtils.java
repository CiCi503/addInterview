package chaperfive;

import java.util.Arrays;

public class MyStringUtils {
    /**
     * �ж������ַ����Ƿ�Ϊ���δ�
     */
    /*
     * ����1�����ַ���ת�������飬���Ǳ��δʣ���ô��������������Ԫ��˳����һ�µ�
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
     * ����2�������ַ������ַ�����ASCII�ַ�����ôʹ��һ������Ϊ256����������¼ÿ���ַ����ַ����г��ֵĳ���
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
