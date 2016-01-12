package chapterfour;

public class Other {
    
    /**
     * 题目：实现函数doublePower(double base, int exponent)，求base的exponent次方，
     * 不得使用库函数，同时不需要考虑大数问题。
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
            if ((absExponent & 0x1) == 1) {// 奇数
                result *= base;
            }
            return result;
        }
    }
    
    /**
     * 题目：打印1到最大的n位数。
     * 输入数字n，按顺序打印出从1最大的n位十进制。比如输入3，
     * 则打印出1,2,3...直到最大的3位数，即999
     */
    /*
     * n并没有限制，最大的n位数可能超过long类型(8 byte)，9223372036854775807，导致溢出，
     * 所以本题是个大数问题。
     * 最常用的也是最容易的方法是使用字符串或者数组表示大数
     * 
     * 方法1：
     */
    public static void print1ToMaxOfDigits(int n) {
        if (n <= 0) {
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
    // 打印数组中的字符组成的数字(去掉前面填位的0)
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
        System.out.println();
    }
    // 做数字的递增
    private static boolean increament(char[] numArr) {
        boolean isOverFlow = false;
        int carry = 0;
        int arrLen = numArr.length;
        int digitSum = 0;
        for (int i = arrLen - 1; i >= 0 ; i--) {
            digitSum = numArr[i] - '0' + carry;
            if (i == arrLen - 1) {// 每一次新的递增都是从最后一位数加1开始的
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
                break;
            }
        }
        return isOverFlow;
    }
    /* 
     * 方法2：将问题转换成全排列。
     * n位数，每一位上的数字都是0~9中的一个，
     * 那么这个问题实际上就是n个从0到9的全排列。
     */
    public static void print1ToMaxOfDigits2(int n) {
        if (n <= 0) {
            return;
        }
        char[] numArr = new char[n];
        for (int i = 0; i < 10; i++) {
            numArr[0] = (char) ((char)i + '0');
            print1ToMaxOfDigits2Recurse(numArr, n, 0);
        }
    }

    private static void print1ToMaxOfDigits2Recurse(char[] numArr, int len,
            int index) {
        if (index == len - 1) {// 递归出口
            printNumer(numArr);
            return;
        }
        for (int j = 0; j < 10; j++) {
            numArr[index + 1] = (char) ((char)j + '0');// 设置后一位
            print1ToMaxOfDigits2Recurse(numArr, len, index + 1);
        }
    }
    
}
