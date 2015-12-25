package chaperfive;

import java.util.Arrays;

public class MyStringUtils {
    /**
     * �ж������ַ����Ƿ�Ϊ���δ�
     */
    /*
     * ����1�����ַ���ת�������飬���Ǳ��δʣ���ô��������������Ԫ��˳����һ�µ�
     * ��������Ч�ʽϵͣ����ǱȽ�ֱ��
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
        // ���߽���������תΪ�ַ��������ж�
//        return new String(arr1).equals(new String(arr2));
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
    
    /**
     * ����һ���ַ���str,������ȫ�������ִ������������ֻ��
     * 
     * Ҫ�󣺺���С����,�硰A1.3��������1��3
     * ������������ִ���������-�ţ����������ֵ�����Ϊ����ʱ��������Ϊ��������Ϊ����
     * ���������ֵ�-�Ų�������
     * ���磺��A-1BC--12������������Ϊ-1��12
     * 
     */
    public static int numSumInStr(String str) {
        if (str == null) {
            return 0;
        }
        boolean sign = true;// ��ʾnum������
        int cur = 0;
        int sum = 0;
        int num = 0;// �����Ӵ���ʾ����ֵ
        for (int i = 0; i < str.length(); i++) {
            cur = str.charAt(i) - '0';
            if (cur < 0 || cur > 9) {
                sum += num;// �ڷ�����ʱ���мӺ�
                num = 0;// num��0
                if (str.charAt(i) == '-') {
                    if (i - 1 > -1 && str.charAt(i - 1) == '-') {// ���ֵڶ���-��
                        sign = !sign;
                    } else {// ֻ������һ��-�ţ�����
                        sign = false;
                    }
                } else {// û�г���-�ţ�����
                    sign = true;
                }
            } else {
                num = num * 10 + (sign ? cur : -cur);
            }
        }
        sum += num;// �����һ�μӺͣ��ַ��������ֽ�β�������
        return sum;
    }
    
}
