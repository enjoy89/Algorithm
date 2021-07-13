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
