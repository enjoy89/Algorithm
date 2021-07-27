/**
 * 배열을 이용한 큐 구현
 * 가장 먼저 추가된 데이터가 가장 먼저 추출 (FIFO:First In First Out)
 */
public class Queue_array {
    private int[] queue;
    private int front;  // 시작 인덱스
    private int rear;   // 마지막 인덱스
    private int size;

    public Queue_array(int size) {
        this.size = size;
        this.front = -1;
        this.rear = -1;
        this.queue = new int[size];
    }

    // 큐에 새로운 데이터를 추가
    // rear 인덱스를 하나 증가시켜 그 공간에 데이터 삽입
    public void enqueue(int data) {
        queue[++rear] = data;
    }

    // front 인덱스에 위치한 데이터를 삭제
    public void dequeue() {
        if (isEmpty()) {
            return;
        }
        front = front + 1;
        queue[front] = 0;
    }

    public boolean isEmpty() {
        return (front == rear);
    }

    public void print_queue() {
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue_array queue = new Queue_array(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.print_queue();
        queue.dequeue();

        System.out.println();
        queue.print_queue();
    }
}
