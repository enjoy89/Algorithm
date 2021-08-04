import java.util.Scanner;

public class BinarySearchTree {

    public static class Node {
        public Node left, right;
        public char data;

        public Node(char data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public char getData() {
            return data;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private Node root;
    private boolean addReturn;
    private char deleteReturn;

    public BinarySearchTree() {
        this.root = null;
    }

    public char find(char target) {
        return find(root, target);
    }

    // 트리에서 target값을 저장하고 있는 노드를 탐색하는 메소드
    public char find(Node localRoot, char target) {
        if (localRoot == null) {
            return 0;
        }
        if (localRoot.getData() == target) {
            return localRoot.getData();
        } else if (localRoot.getData() > target) {      // 현재노드보다 target값이 더 작으면
            return find(localRoot.getLeft(), target);   // 왼쪽 서브트리 재귀 탐색
        } else {                                        // 현재노드보다 target값이 더 크면
            return find(localRoot.getRight(), target);  // 오른쪽 서브트리 재귀 탐색
        }
    }

    public boolean add(char item) {
        root = add(root, item);
        return addReturn;
    }

    // 트리에 새로운 노드를 삽입하는 메소드
    public Node add(Node localRoot, char item) {
        if (localRoot == null) {
            addReturn = true;
            return new Node(item);
        } else if (localRoot.getData() == item) {   // 트리에 이미 동일한 값이 존재함
            addReturn = false;
            return localRoot;
        } else if (localRoot.getData() > item) {
            localRoot.left = add(localRoot.getLeft(), item);    // 왼쪽 서브트리 재귀 삽입
            return localRoot;
        } else {
            localRoot.right = add(localRoot.getRight(), item);  // 오른쪽 서브트리 재귀 삽입
            return localRoot;
        }
    }

    public char delete(char target) {
        root = delete(root, target);
        return deleteReturn;
    }

    // 트리에 존재하는 노드를 삭제하는 메소드
    public Node delete(Node localRoot, char item) {
        if (localRoot == null) {
            deleteReturn = 0;
            return localRoot;
        }
        if (localRoot.getData() > item) {
            localRoot.left = delete(localRoot.getLeft(), item);
            return localRoot;
        } else if (localRoot.getData() < item) {
            localRoot.right = delete(localRoot.getRight(), item);
            return localRoot;
        } else {
            // 삭제할 노드의 자식 노드가 1개 존재할 경우
            deleteReturn = localRoot.getData();
            if (localRoot.left == null) {       // 삭제할 노드의 왼쪽 노드가 비어있을 경우
                return localRoot.getRight();    // 삭제할 노드의 오른쪽 노드만이 존재
            } else if (localRoot.right == null) {
                return localRoot.getLeft();
            } else {
                // 삭제할 노드의 자식 노드가 2개 존재할 경우
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // 삭제할 노드의 왼쪽 자식 노드 중에서 가장 큰 값을 삭제할 노드에 데이터로 저장
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    // parent 노드는 삭제할 노드의 왼쪽 자식 노드임
    // parent 노드의 자식 노드 중 가장 큰 값을 반환한다.
    public char findLargestChild(Node parent) {
        if (parent.right.right == null) {
            char returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    public void inorder() {
        inorder(root);
    }

    // left -> root -> right
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.getLeft());
            System.out.print(root.getData() + " ");
            inorder(root.getRight());
        }
    }

    // 이진탐색트리의 최대깊이를 반환하는 메소드
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.getLeft());
        int rightDepth = maxDepth(root.getRight());
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 이진탐색트리의 최소깊이를 반환하는 메소드
    public int minDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.getLeft());
        int rightDepth = minDepth(root.getRight());
        return Math.min(leftDepth, rightDepth) + 1;

    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("트리의 상태: ");
            bst.inorder();
            System.out.println();

            System.out.print("입력하시오: ('1': 종료 | '2': 노드 추가): ");
            int num = input.nextInt();

            // 사용자가 1을 입력하면 동작을 중단하고 생성된 트리의 maximum level과 minimum level를 화면에 출력하고 종료.
            if (num == 1) {
                System.out.print("Maximum depth: " + bst.maxDepth(bst.root));
                System.out.println();
                System.out.print("Minimum depth: " + bst.minDepth(bst.root));
                break;
                // 사용자가 2를 입력하면 노드 추가
            } else if (num == 2) {
                System.out.print("추가할 노드의 문자를 입력하시오: ");
                char item = input.next().charAt(0);
                bst.add(item);
            }
        }
    }
}
