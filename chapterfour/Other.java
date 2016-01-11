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
}
