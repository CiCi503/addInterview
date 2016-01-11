package chapterfour;

public class RecurseAndIteration {
    /**
     * 쳲���������
     */
    
    /*
     * �ⷨ1�����ڵݹ�ʵ��
     * �ڱ��������ֽⷨ���ںܴ����⣬����кܶ��ظ��ļ���
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
     * �ⷨ2������ѭ��ʵ��
     * ʱ�临�Ӷ�O(n)��ʵ�ýⷨ����ѡ
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
     * �ⷨ3�����ھ���˷�
     * 
     * ʱ�临�Ӷ�ΪO(lgn)����������
     */
    
    // ��׼����
    private static final long[][] BASE_MATRIX = new long[][] {{1, 1},{1, 0}};
    
    // �����������
    private static long[][] matrixMultiply(long[][] matrix1, long[][] matrix2) {
        long m00 = matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0];
        long m01 = matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1];
        long m10 = matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0];
        long m11 = matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1];
        return new long[][]{{m00, m01}, {m10, m11}};
    }
    
    // ����Ľ׳�
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
    
    /**
     * ������̨�����⣺һֱ����һ�ο�����1��̨�ף�Ҳ������2������̨����n������ʱ��һ���ж�����������
     * ������Ϊfibonacci���У�ֻ�ǳ�ʼֵ�е㲻ͬ��n=1ʱ��ֻ��1��������n=2ʱ����2�֡�
     */
    public static long getMethodsCount(int stepNum) {
        long result = 0;
        long pre = 1;
        long suf = 2;
        if (stepNum <= 0) {
            throw new RuntimeException("The number of step must be a positive one!");
        } else if (stepNum == 1) {
            result = 1;
        } else if (stepNum == 2) {
            result = 2;
        } else {
            for (int i = 3; i <= stepNum; i++) {
                result = pre + suf;
                pre = suf;
                suf = result;
            }
        }
        return result;
    }
    
}
