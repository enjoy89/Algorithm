/**
 * Counting sort (계수 정렬)
 * 배열의 원소끼리 비교하는 경우가 없으므로 비교적 속도는 빠른 편이다.
 * 정렬하고자 하는 수의 범위가 커질수록 효율이 떨어진다. (메모리 낭비)
 * 1. 초기 배열을 순회하며 각각의 원소의 값을 index로 가지는 배열에 counting을 증가시킨다.
 * 2. counting배열의 크기는 초기 배열의 원소값의 최대값 + 1으로 설정한다.
 * 3. counting배열의 원소를 누적합으로 바꿔준다.
 * 4. counting배열을 순회하며 누적합을 통해 어떤 index에 위치해야하는지 맵핑 시킨다.
 * 시간 복잡도 O(N)
 */
public class CountingSort {

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 5, 10, 7, 1, 1, 4, 13};
        int[] result = new int[arr.length];
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // counting 배열의 인덱스값 증가
        int[] counting = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            counting[arr[i]]++;
        }

        // 누적합 구하기
        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        // result 배열에 counting 배열의 원소값을 새로운 인덱스값으로 정하여 값을 저장한다.
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            counting[arr[i]]--;
            result[counting[arr[i]]] = value;
        }

        System.out.println("array");    // 정렬 전
        printArray(arr);

        System.out.println("result");   // 정렬 후
        printArray(result);
    }
}
