/**
 * Counting sort (계수 정렬)
 * 1. 초기 배열을 순회하며 각각의 원소의 값을 index로 가지는 배열에 counting을 증가시킨다.
 * 2. counting 배열의 크기 = 초기배열의 원소 중 (최대값 + 1)로 설정한다.
 * 3. counting 배열의 원소를 누적합으로 바꿔준다.
 * 4. counting 배열을 순회하며 누적합을 통해 어떤 index에 위치해야하는지 맵핑 시킨다.
 * 배열의 원소끼리 비교하는 경우가 없으므로 비교적 속도는 빠른 편이다.
 * 정렬하고자 하는 수의 범위가 커질수록 효율이 떨어진다. (메모리 낭비)
 * 시간 복잡도 O(N)
 */
public class Counting_Sort {

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input_arr = {3, 8, 5, 10, 7, 1, 1, 4, 13};
        int[] result = new int[input_arr.length]; // 결과를 저장할 배열
        int max = 0;

        // 입력 배열의 최대값을 탐색
        for (int i = 0; i < input_arr.length; i++) {
            if (max < input_arr[i]) {
                max = input_arr[i];
            }
        }

        // counting 배열의 인덱스값 증가
        int[] counting = new int[max + 1];
        // 배열의 원소값을 인덱스로 가지는 counting 배열
        for (int i = 0; i < input_arr.length; i++) {
            counting[input_arr[i]]++;
        }

        // 누적합 구하기
        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        // result 배열에 counting 배열의 원소값을 새로운 인덱스값으로 정하여 값을 저장한다.
        for (int i = input_arr.length - 1; i >= 0; i--) {
            int value = input_arr[i];
            counting[input_arr[i]]--;
            result[counting[input_arr[i]]] = value;
        }

        System.out.println("array");    // 정렬 전
        printArray(input_arr);

        System.out.println("result");   // 정렬 후
        printArray(result);
    }
}
