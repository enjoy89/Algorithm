import java.util.Scanner;

/**
 * 사용자로부터 노드에 입력할 데이터를 입력 받아 노드를 생성한 후 이진 트리를 만드는 프로그램.
 * 노드 1개에 문자 하나 저장
 * 문자의 중복은 허용 하지 않음 -> 사용자가 트리에 존재하는 문자를 입력하는 경우 다른 문자를 입력하도록 요구하는 메시지를 출력.
 */
public class BinaryTree {

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

    public Node root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(char data, BinaryTree leftTree, BinaryTree rightTree) {
        root = new Node(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public void insert(char data) {
        root = insert(root, data);
    }

    public Node insert(Node node, char data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (node.getRight() == null) {
                node.right = insert(node.right, data);
            } else {
                node.left = insert(node.left, data);
            }
        }
        return node;
    }

    public char getData() {
        return root.getData();
    }

    // 트리에 data를 저장하는 노드가 존재하는지 확인하는 메소드
    public boolean findData(Node root, char data) {
        if (root == null) {
            return false;
        }
        if (root.getData() == data) {
            return true;
        }
        boolean find = findData(root.getRight(), data);
        if (find) {
            return true;
        }
        return findData(root.getLeft(), data);
    }

    // 트리에 data를 저장하는 노드를 찾아 반환하는 메소드
    public Node search(Node root, char data) {
        if (root == null) {
            return null;
        }
        if (root.getData() == data) {
            return root;
        }
        Node result = null;
        if (root.getLeft() != null) {
            result = search(root.getLeft(), data);
        }

        if (result == null && root.getRight() != null) {
            result = search(root.getRight(), data);
        }
        return result;
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

    public void preorder() {
        preorder(root);
    }

    // root -> left -> right
    public void preorder(Node root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }

    public void postorder() {
        postorder(root);
    }

    // left -> right -> root
    public void postorder(Node root) {
        if (root != null) {
            postorder(root.getLeft());
            postorder(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();

        while (true) {
            System.out.print("트리의 상태: ");
            binaryTree.preorder();
            System.out.println();

            System.out.print("입력하시오: ('1': 종료 | '2': 노드 추가): ");
            int num = input.nextInt();

            // 사용자가 1을 입력하는 경우 -> 트리 생성 종료
            // 트리 순회 결과를 화면에 출력하고 프로그램을 종료함.
            if (num == 1) {
                System.out.print("Inorder traversal: ");
                binaryTree.inorder();
                System.out.println();
                System.out.print("Preorder traversal: ");
                binaryTree.preorder();
                System.out.println();
                System.out.print("Postorder traversal: ");
                binaryTree.postorder();
                break;
            }
            //사용자가 2를 입력하는 경우 -> 트리에 노드를 생성하고 추가함
            else if (num == 2) {
                // 만약 트리가 비어있는 경우
                if (binaryTree.root == null) {
                    System.out.print("트리가 빈 상태입니다. 추가할 노드를 입력해주세요: ");
                    char data = input.next().charAt(0);
                    binaryTree.insert(data);
                } else {
                    // 트리가 비어있는 상태가 아닌 경우
                    System.out.print("노드를 추가할 위치를 입력하시오: ('3' or '4') : ");
                    int num2 = input.nextInt();

                    // 사용자가 3을 입력하는 경우 -> 현재 트리에 존재하지 않은 문자를 저장하는 노드를 생성.
                    // 기존 트리는 새로운 트리의 왼쪽 서브트리로 처리.
                    if (num2 == 3) {
                        BinaryTree currentTree = new BinaryTree();
                        System.out.print("추가할 노드를 입력해주세요: ");
                        while (true) {
                            char data = input.next().charAt(0);
                            if (binaryTree.findData(binaryTree.root, data)) {    // 입력 받은 데이터가 트리에 존재하는지 확인
                                System.out.print("이미 존재하는 문자입니다. 다시 입력해주세요: ");
                            } else {
                                currentTree.insert(data);
                                break;
                            }
                        }
                        currentTree.root.setLeft(binaryTree.root);      // 기존 트리는 새로운 루트 노드의 왼쪽 서브트리로 처리함.
                        binaryTree = currentTree;
                    }

                    // 사용자가 4를 입력하는 경우 -> (1)사용자에게 트리에 존재하는 문자를 입력 요구
                    // (2)존재하는 문자 입력이 끝나면, (3)트리에 존재하지 않는 문자를 입력 요구
                    // (4)트리에 존재하는 문자의 노드에 트리에 존재하지 않는 문자의 노드를 연결
                    else if (num2 == 4) {
                        BinaryTree currentTree = new BinaryTree();
                        BinaryTree newTree = new BinaryTree();
                        System.out.print("트리에 이미 존재하는 문자를 입력해주세요:  ");
                        char data = input.next().charAt(0);     // 이미 존재하는 문자
                        // 트리에 존재하는지 확인
                        if (binaryTree.findData(binaryTree.root, data)) {
                            currentTree.root = binaryTree.search(binaryTree.root, data);
                        }
                        System.out.print("새로 추가할 노드를 입력해주세요: ");
                        while (true) {
                            char data2 = input.next().charAt(0);
                            if (binaryTree.findData(binaryTree.root, data2)) {
                                System.out.print("이미 존재하는 문자입니다. 다시 입력하세요: ");
                            } else {    // 사용자가 트리에 존재하지 않는 문자를 입력하는 경우
                                newTree.insert(data2);
                                break;
                            }
                        }
                        // (1) 과정의 노드의 왼쪽 서브트리가 null일 경우 새로 생성한 노드를 왼쪽 서브트리로 추가함
                        if (currentTree.root.getLeft() == null) {
                            currentTree.root.setLeft(newTree.root);
                            // 왼쪽 서브트리가 null이 아니고 오른쪽 서브트리가 null일 경우 새로 생성한 노드를 오른족 서브트리로 추가함
                        } else if (currentTree.root.getLeft() != null && currentTree.root.getRight() == null) {
                            currentTree.root.setRight(newTree.root);
                            // 왼쪽 오른쪽 서브트리가 모두 null이 아닌 경우 왼쪽 서브트리에 추가한 후
                            // 기존의 왼쪽 서브트리는 새로 생성한 노드의 왼쪽 서브트리로 설정함
                        } else if (currentTree.root.getLeft() != null && currentTree.root.getRight() != null) {
                            Node temp = currentTree.root.getLeft();
                            currentTree.root.setLeft(newTree.root);
                            newTree.root.setLeft(temp);
                        }
                    }
                }
            }
        }
    }
}

