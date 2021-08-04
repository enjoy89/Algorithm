import java.util.ArrayList;
import java.util.Scanner;

/**
 * 정수를 저장하는 자료구조(Heap) 구현
 * 자료구조에는 0 혹은 양수만 존재
 */
public class Heap {
    private ArrayList<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public int getRightChild(int index) {
        return getLeftChild(index) + 1;
    }

    // 삽입
    public void insert(int data) {
        heap.add(data);
        int p = heap.size() - 1;

        while (heap.get(getParent(p)) > heap.get(p)) {
            // swap
            int temp = heap.get(getParent(p));
            heap.set(getParent(p), heap.get(p));
            heap.set(p, temp);
            p = getParent(p);
        }
    }

    // 출력
    public void printHeap() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * heap에 저장되어 있는 숫자들 중에서 data보다 작은 값이 있는지 확인하는 메소드
     *
     * @param data
     * @return 작은 값이 있으면 true, 없으면 false 반환
     */
    public boolean find(int data) {
        boolean c = false;
        for (Integer integer : heap) {
            if (integer > data) {
                c = true;
                break;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Heap heap = new Heap();

        while (true) {
            System.out.print("Heap: ");
            heap.printHeap();

            System.out.print("Heap에 저장할 숫자를 입력해주세요(음수 입력 시 종료): ");
            int num = input.nextInt();

            // 사용자가 음수를 입력하면 삽입 동작은 종료.
            // 사용자로부터 숫자 Q를 입력 받음
            if (num < 0) {
                System.out.print("임의의 숫자(Q)를 입력해주세요: ");
                int q = input.nextInt();
                // 자료구조에 저장된 숫자들 중 입력받은 숫자(Q)보다 작은 수가 존재하는지 확인
                if (heap.find(q)) {
                    System.out.println("OK");   // Q 값보다 작은 수가 없으면 OK
                } else {
                    System.out.println("NO");   // Q 값보다 작은 수가 있으면 NO
                }
                break;
            } else heap.insert(num);
        }
    }
}
