import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 배열을 이용한 Stack
 * 가장 나중에 추가한 데이터를 가장 먼저 추출 (LIFO:Last In First Out)
 * top에서만 삽입, 삭제, 읽기가 가능함
 */
public class Stack_array {
    private int[] stack;
    private int top;    // 가장 최근에 추가한 데이터
    private int size;

    public Stack_array(int size) {
        this.size = size;
        this.top = -1;
        this.stack = new int[size];
    }

    // 스택에 데이터 추가
    public void push(int data) {
        stack[++top] = data;
    }

    // top 위치에 존재하는 데이터 삭제
    public void pop() {
        stack[top--] = 0;
    }

    // top 위치에 존재하는 데이터 확인
    public void peek() {
        System.out.println(stack[top]);
    }

    // 스택에서 해당 데이터가 존재하는지 탐색
    // 만약 데이터가 존재한다면 인덱스값을 반환하고, 존재하지 않으면 -1를 반환함
    public int search(int data) {
        for (int i = 0; i < stack.length; i++) {
            if (stack[i] == data) {
                return (i + 1);
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print_stack() {
        for (int i = stack.length - 1; i >= 0; i--) {
            if (i == stack.length - 1) {
                System.out.println(stack[stack.length - 1] + " <-- top");
            } else {
                System.out.println(stack[i]);
            }
        }
    }
}
