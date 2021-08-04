import java.io.*;
import java.util.*;

/**
 * 그래프 및 깊이 우선 & 넓이 우선 탐색 방법 구현.
 * 파일을 읽어 들여 그래프를 구현한 후, 해당 그래프에 존재하는 노드를 방문하는 프로그램.
 * 사용자로부터 입력 받은 시작 노드로부터 깊이 우선 탐색한 결과와 넓이 우선 탐색한 결과를 화면에 출력.
 * 엣지는 Adjacency matrix 방법으로 표현
 */
public class Graph_traversal {

    private static int[][] arrGraph;
    private static int num;
    private static final ArrayList<Integer> row = new ArrayList<>();
    private static final ArrayList<Integer> column = new ArrayList<>();

    // 그래프 추가 (양방향)
    public static int[][] getGraph() {
        arrGraph = new int[num][num];
        for (int i = 0; i < row.size(); i++) {
            arrGraph[row.get(i)][column.get(i)] = 1;
            arrGraph[column.get(i)][row.get(i)] = 1;
        }
        return arrGraph;
    }

    public static void printMatrix(int[][] arr) {
        System.out.println("[Adjacency matrix]");
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    // 깊이 우선 탐색
    public static void DepthFirstSearch(int[][] matrix, int v) {
        // 노드 방문 여부
        boolean[] visited = new boolean[matrix.length];

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> output = new ArrayList<>();

        // 시작 vertex 값을 스택에 넣는다.
        stack.push(v);
        visited[v] = true;
        output.add(v);

        boolean check;

        while (!stack.isEmpty()) {
            int top = stack.peek();
            check = false;

            // 해당 노드와 인접한 노드들을 모두 스택에 push.
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[top][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    output.add(i);
                    check = true;
                    top = i;
                }
            }
            // 더이상 연결된 노드가 없고, 아직 방문하지 못한 정점을 찾지 못한 경우 pop.
            if (!check) {
                stack.pop();
            }
        }
        printOutput(output);
    }

    public static void printOutput(ArrayList<Integer> output) {
        for (Integer integer : output) {
            System.out.print("[" + integer + "] ");
        }
    }

    // 넓이 우선 탐색
    public static void BreadthFirstSearch(int[][] matrix, int v) {
        boolean[] visited = new boolean[matrix.length];
        ArrayList<Integer> output = new ArrayList<>();
        visited[v] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while (!queue.isEmpty()) {
            output.add(queue.peek());
            int x = queue.poll();

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[x][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        printOutput(output);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file = new File("./src/12주차 data_sample.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            boolean check = true;

            while ((str = br.readLine()) != null) {
                if (str.length() == 0) {
                    continue;
                }
                if (check) {
                    num = Character.getNumericValue(str.charAt(0));
                    if (str.equals(" ")) {
                        break;
                    }
                    check = false;
                } else {
                    String arr = str;
                    // Adjacency matrix 정보 무시
                    if (arr.charAt(1) != '-') {
                        break;
                    }
                    row.add(Character.getNumericValue(arr.charAt(0)));
                    column.add(Character.getNumericValue(arr.charAt(2)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMatrix(getGraph());
        System.out.println("Input start node >> ");
        int v = input.nextInt();

        System.out.print("DFS result: ");
        DepthFirstSearch(arrGraph, v);
        System.out.print("\nBFS result: ");
        BreadthFirstSearch(arrGraph, v);
    }
}
