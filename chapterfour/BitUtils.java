package chapterfour;

public class BitUtils {
    
    /**
     * ��Ŀ������������1�ĸ���
     */
    /*
     * �ⷨ1:num�������ƣ�ֱ��Ϊ0
     * ע�⣺ʹ���޷������ƣ����������num<0ʱ����������ѭ��
     */
    public static int getCountOfOne(int num) {
        int count = 0;
        while (num != 0 ) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>>= 1;
        }
        return count;
    }
    
    /*
     * �ⷨ2����־λ��1��ʼ���������ƣ�ֱ��Ϊ0 
     */
    public static int getCountOfOne2(int num) {
        int count = 0;
        int flag = 1;// flag��count�ȳ�
        while (flag != 0) {
            if ((num & flag) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }
    
    /*
     * �ⷨ3������
     * ʹ��num &= (num - 1)��ȥ�������������ұߵ��Ǹ�1
     */
    public static int getCountOfOne3(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num &= (num - 1);
        }
        return count;
    }

    
    
    
}
