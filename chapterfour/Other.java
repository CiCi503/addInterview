package chapterfour;

import java.util.BitSet;

public class Other {
    
    /**
     * ��Ŀ��ʵ�ֺ���doublePower(double base, int exponent)����base��exponent�η���
     * ����ʹ�ÿ⺯����ͬʱ����Ҫ���Ǵ������⡣
     */
    public static double doublePower(double base, int exponent) {
        double result = 1.0;
        if (doubleEquals(base, 0.0)) {
            if (exponent <= 0) {
                throw new RuntimeException("Invalid param!");
            } else {
                result = 0.0;
            }
        } else {
            int absExponent = exponent < 0 ? -exponent : exponent;
            result = powerHelper2(base, absExponent);
            if (exponent < 0) {
                result = 1.0 / result;
            }
        }
        return result;
    }
    
    private static boolean doubleEquals(double num1, double num2) {
        if (num1 - num2 > -0.0000001 && num1 - num2 < 0.0000001) {
            return true;
        } else {
            return false;
        }
    }
    
    private static double powerHelper(double base, int absExponent) {
        double result = 1.0;
        for (int i = 0; i < absExponent; i++) {
            result *= base;
        }
        return result;
    }
    
    private static double powerHelper2(double base, int absExponent) {
        if (absExponent == 0) {
            return 1;
        } else if (absExponent == 1){
            return base;
        } else {
            double result = powerHelper2(base, absExponent >>> 1);
            result *= result;
            if ((absExponent & 0x1) == 1) {// ����
                result *= base;
            }
            return result;
        }
    }
    
    /**
     * ��Ŀ����ӡ1������nλ����
     * ��������n����˳���ӡ����1����nλʮ���ơ���������3��
     * ���ӡ��1,2,3...ֱ������3λ������999
     */
    /*
     * n��û�����ƣ�����nλ�����ܳ���long����(8 byte)��9223372036854775807�����������
     * ���Ա����Ǹ��������⡣
     * ��õ�Ҳ�������׵ķ�����ʹ���ַ������������ʾ����
     * 
     * ������������ַ��������������
     * ����1��
     */
    public static void print1ToMaxOfDigits(int n) {
        if (n < 1) {
            return;
        }
        char[] numArr = new char[n];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = '0';
        }
        while (!increament(numArr)) {
            printNumer(numArr);
        }
        
    }
    // ��ӡ�����е��ַ���ɵ�����(ȥ��ǰ����λ��0)
    private static void printNumer(char[] numArr) {
        int numArrLen = numArr.length;
        boolean isBegin = true;
        for (int i = 0; i < numArrLen; i++) {
            if (isBegin && numArr[i] != '0') {
               isBegin = false; 
            }
            if (!isBegin) {
                System.out.print((char)numArr[i]);
            }
        }
        if (!isBegin) {// ����������Ϊ0����ô��ӡ��ɺ���л���
            System.out.println();
        }
    }
    // �����ֵĵ���
    private static boolean increament(char[] numArr) {
        boolean isOverFlow = false;
        int carry = 0;
        int arrLen = numArr.length;
        int digitSum = 0;
        for (int i = arrLen - 1; i >= 0 ; i--) {
            digitSum = numArr[i] - '0' + carry;
            if (i == arrLen - 1) {// ÿһ���µĵ������Ǵ����һλ����1��ʼ��
                digitSum++;
            }
            if (digitSum >= 10) {
                if (i == 0) {
                    isOverFlow = true;
                } else {
                    carry = 1;
                    digitSum -= 10;
                    numArr[i] = (char)('0' + (char)digitSum);
                }
            } else {
                numArr[i] = (char)('0' + (char)digitSum);
            }
        }
        return isOverFlow;
    }
    /* 
     * ����2��������ת����ȫ���С�
     * nλ����ÿһλ�ϵ����ֶ���0~9�е�һ����
     * ��ô�������ʵ���Ͼ���n����0��9��ȫ���С�
     */
    public static void print1ToMaxOfDigits2(int n) {
        if (n < 1) {
            return;
        }
        char[] numArr = new char[n];
        print1ToMaxOfDigits2Recurse(numArr, 0);
    }

    private static void print1ToMaxOfDigits2Recurse(char[] numArr,  int index) {
        if (index == numArr.length) {// �ݹ����
            printNumer(numArr);
        } else {
            for (int j = 0; j < 10; j++) {
                numArr[index] = (char) ((char)j + '0');// ���ú�һλ
                print1ToMaxOfDigits2Recurse(numArr, index + 1);
            }
        }
      
    }
    
    /*
     * ˼����һ��char���ͱ���ռ2���ַ���Ҳ����16bit�����Ա�ʾ0~65535��
     * ������ֻ��Ҫ0-9��10�����֣�����Ȼ����˿ռ���˷ѡ�
     */
    // TODO optimize the memory
    public static void print1ToMaxOfDigits3(int n) {

    }
    

    
}
