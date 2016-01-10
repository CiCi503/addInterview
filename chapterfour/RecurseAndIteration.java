package chapterfour;

public class RecurseAndIteration {
    /**
     * 斐波拉契数列
     */
    
    /*
     * 解法1：基于递归实现
     * 在本题中这种解法存在很大问题，会进行很多重复的计算
     */
    public static long fibonacci1(int end) {
        if (end <= 0) {
            return 0;
        }
        if (end == 1) {
            return 1;
        }
        return fibonacci1(end - 1) + fibonacci1(end - 2);
    }
    
    /*
     * 解法2：基于循环实现
     * 时间复杂度O(n)，实用解法，首选
     */
    public static long fibonacci2(int end) {
        long result = 0;
        long preNum = 0;
        long sufNum = 1;
        if (end == 0) {
            result = 0;
        }
        if (end == 1) {
            result = 1;
        }
        for (int i = 2; i <= end; i++) {
            result = preNum + sufNum;
            preNum = sufNum;
            sufNum = result;
        }
        return result;
    }
    /*
     * 解法3：基于矩阵乘法
     * 
     */
    
    // 基准数组
    private static final long[][] BASE_MATRIX = new long[][] {{1, 1},{1, 0}};
    
    // 两个矩阵相成
    private static long[][] matrixMultiply(long[][] matrix1, long[][] matrix2) {
        long m00 = matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0];
        long m01 = matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1];
        long m10 = matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0];
        long m11 = matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1];
        return new long[][]{{m00, m01}, {m10, m11}};
    }
    
    // 矩阵的阶乘
    private static long[][] matrixPow(long[][] matrix, int n) {
        if (n == 1) {
            matrix = BASE_MATRIX;
        } else if (n % 2 == 0) {
            matrix = matrixPow(matrix, n / 2);
            matrix = matrixMultiply(matrix, matrix);
        } else if (n % 2 == 1) {
            matrix = matrixPow(matrix, (n - 1) / 2);
            matrix = matrixMultiply(matrix, matrix);
            matrix = matrixMultiply(matrix, BASE_MATRIX);
        }
        return matrix;
    }
    
    public static long fibonacci3(int end) {
        long result = 0;
        long[][] baseMatrix = BASE_MATRIX;
        if (end < 0) {
            throw new RuntimeException("Wrong order number!");
        } else if (end == 0) {
            result = 0;
        } else if (end == 1) {
            result = 1;
        } else {
            long[][] matrix = matrixPow(baseMatrix, end - 1);
            result = matrix[0][0];
        }
        return result;
        
    }
    
}
