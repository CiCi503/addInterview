package chaperone;

import java.util.PriorityQueue;

/**
 * ��Ŀ���õ��������е���λ��
 */
/*
 * ����λ����Ҳ�����ҵ�����һ�����������������Ԫ�ض�����ҪС��
 * �Ҳ�����Ԫ�ض�����Ҫ��ʵ���ϲ�����Ҫ�����ߵ�Ԫ�ض�����ֻ��Ҫ�ҵ�������ĺ��ұ���С�ģ�
 * ����뵽�����Ѻ���С�ѣ�java���ж�Ӧ�����ݽṹPriorityQueue��Ĭ��Ϊ��Ȼ����
 * 
 * ������������Ϊ����ʱ����С�ѵĳ���ҪС�����ѣ����Դ�ʱ����λ���������ѵĶѶ�Ԫ��;
 * ż��ʱ��Ϊ�����ѶѶ�Ԫ�ص�ƽ������
 */
public class MedianInStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    
    public void Insert(Integer num) {
        if (((minHeap.size() + maxHeap.size()) & 1) == 0) {// ����Ϊż��
            if (num < minHeap.peek()) {// num���ұ���С��ֵ��ҪС����ônumӦ�üӵ����
                maxHeap.offer(num);
            } else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
        } else {
            if (num > maxHeap.peek()) {// num��������ֵ��Ҫ����ônumӦ�ӵ��ұ�
                minHeap.offer(num);
            } else {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }
        }
    }

    public Double GetMedian() {
        if (minHeap.size() != maxHeap.size()) {
            return Double.valueOf(maxHeap.peek());
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
