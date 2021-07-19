import java.util.Scanner;

/**
 * SingleLinkedList
 * 리스트에서 사용자로부터 원하는 구간을 삭제하는 동작이 포함됨 -> remove()
 * 예시) List state: 1 -> 2 -> 3 -> 4 -> 5
 *      삭제할 구간 설정
 *      start value: 1
 *      end value: 3
 *      List state: 4 -> 5
 */
public class SingleLinkedList {

    private static class Node {
        public String data;
        public Node next;

        public Node(String dataItem) {
            this.data = dataItem;
            this.next = null;
        }

        public Node(String dataItem, Node nodeRef) {
            this.data = dataItem;
            this.next = nodeRef;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextNode) {
            this.next = nextNode;
        }
    }

    private Node head;
    private int size;

    public SingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void traverse() {
        Node nodeRef = head;
        while (nodeRef != null) {
            if (nodeRef.next != null) {
                System.out.print(nodeRef.data);
                System.out.print(" -> ");
            } else {
                System.out.println(nodeRef.data);
            }
            nodeRef = nodeRef.next;
        }
    }

    // index에 해당하는 노드의 데이터를 반환
    public String get(int index) {
        Node node = getNode(index);
        return node.data;
    }

    // index에 해당하는 노드를 반환
    private Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    // index에 존재하는 노드에 그대로 새로운 값을 복사
    public String set(int index, String newValue) {
        Node node = getNode(index);
        String result = node.data;
        node.data = newValue;
        return result;
    }

    // 리스트의 가장 맨앞에 원소를 추가한다.
    // 동작: 현재의 head노드 자리에 새로 추가할 노드를 하나 생성하여 넣어준 후, 원래의 head노드를 가리키게 한다.
    // 원소가 추가 되므로 사이즈 하나 증가
    public void addFirst(String item) {
        head = new Node(item, head);
        size++;
    }

    public void add(int index, String item) {
        if (index == 0) {
            addFirst(item);
        } else {
            Node node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    public void addAfter(Node node, String item) {
        node.next = new Node(item, node.next);
        size++;

    }

    // index에 해당하는 노드를 삭제
    // 동작: 인덱스가 0일 경우 현재 head를 다음 head로 바꿔줌. 인덱스가 0이 아닐 경우 해당 인덱스의 직전 노드까지 curr값을 바꿔줌.
    // 그런다음 curr의 다다음 노드를 가리키게함.
    // 원소가 삭제되었으니 사이즈 하나 감소
    public boolean remove(int index) {
        Node curr = head;
        if (index == 0) {
            head = head.next;
            return true;
        } else {
            for (int i = 1; i < index; i++) {
                if (curr.getNext() == null) {
                    return false;
                }
                curr = curr.getNext();
            }
            curr.setNext(curr.getNext().getNext());
            size--;
            return true;
        }
    }

    // start인덱스에서 end인덱스까지 노드를 삭제
    // 동작: end가 1일 경우 head를 다음 head로 바꿔줌.
    // start가 1일 경우 tail의 다음 노드를 head로 바꿔줌.
    public boolean removeInterval(int start, int end) {
        Node curr = head;
        Node tail = head;
        int sizeCount = (end - start);

        // 방법 1
        if (end == 1) {
            head = head.next;
            return true;
        } else {
            for (int i = 2; i < start; i++) {
                if (curr.getNext() == null) {
                    return false;
                }
                curr = curr.getNext();
            }
            for (int i = 1; i < end; i++) {
                tail = tail.getNext();
            }
            if (start == 1) {
                head = tail.getNext();
                return true;
            }
            curr.setNext(tail.getNext());
            size -= sizeCount;
            return true;

            // 방법 2
//        Node nodeStart = getNode(start - 2);
//        Node nodeEnd = getNode(end);
//
//        if(start < 1 || end > size) {
//            return false;
//        }
//        if (start == 1) {
//            head = nodeEnd;
//        } else if (end == 1) {
//            head = head.next;
//        } else {
//            nodeStart.setNext(nodeEnd);
//        }
//        size -= sizeCount;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        Scanner input = new Scanner(System.in);
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        int count = 1;

        while (true) {
            System.out.print("삽입 값 입력: ");
            String str = input.next();

            if (!str.equals("end")) {
                if (singleLinkedList.size == 0) {
                    singleLinkedList.addFirst(str);
                } else {
                    singleLinkedList.add(count, str);
                    count++;
                }
            } else {
                System.out.print("List state: ");
                singleLinkedList.traverse();
                System.out.println("삭제할 구간 설정");
                System.out.print("start value: ");
                int start = input.nextInt();
                System.out.print("end value: ");
                int end = input.nextInt();

                if (start < 1 || end > singleLinkedList.size) {
                    System.out.println("잘못된 입력값입니다.");
                    break;
                }
                singleLinkedList.removeInterval(start, end);
                System.out.print("List state: ");
                singleLinkedList.traverse();
                break;

            }
            System.out.print("List state: ");
            singleLinkedList.traverse();
        }
    }
}
