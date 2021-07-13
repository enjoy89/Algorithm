public class sort_Exam {

    /**
     * selection sort (선택 정렬)
     * 1. 배열의 0번째 인덱스의 원소를 최소값으로 설정함
     * 2. 최소값과 배열의 원소를 차례대로 비교하여 작은 값을 맨 앞으로 보낸다.
     * 3. 맨 앞의 원소는 정렬이 완료된 상태이므로 제외시킴
     * 4. 새롭게 정의된 최소값과 배열의 원소의 크기를 다시 비교함
     * 5. 배열의 마지막 인덱스까지 순회를 완료한 후, 반목문 종료
     * 데이터의 양이 적을 경우에는 좋은 성능이 나타나지만, 데이터의 양이 커질 수록 속도가 급격히 오래걸려 적절한 사용이 어려움
     * @param arr
     * @return
     */
    public static int[] Selection_sort(int[] arr) {
        int min;
        int temp;

        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[min];
                    arr[min] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * bubble sort (버블 정렬)
     * 1. 배열의 i 번째 원소와 i+1 번째 원소의 크기를 비교하여 큰 원소를 뒤로 이동시킨다.
     * 2. 맨 끝의 원소는 자동으로 정렬되므로 제외함 (내부의 for loop length - 1까지 탐색)
     * 시간복잡도 O(n^2)
     * @param arr
     * @return
     */
    public static int[] Bubble_sort(int[] arr) {
        int temp;

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;

    }

    /**
     * insertion sort (삽입 정렬)
     * 1. 배열의 2번째 원소를 key 값으로 설정한 후, 왼쪽에 위치한 원소들과 크기를 비교하여 둘 중 큰 값을 뒤로 보낸다.
     * 2. 배열 안의 정렬 범위를 한 칸씩 확장해나감
     * 3. 새롭게 정해진 범위에서 위의 과정을 반복함
     * 시간복잡도 O(n^2)
     * @param arr
     * @return
     */
    public static int[] Insertion_sort(int[] arr) {
        int temp;

        for(int i = 1; i<arr.length; i++) {
            int key = i;
            for(int j = i-1; j>=0; j--) {
                if(arr[key] < arr[j]) {
                    temp = arr[key];
                    arr[key] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input_arr = {1, 4, 6, 2, 17, 3, 15, 8, 9};

        System.out.print("정렬 전 배열: ");
        printArray(input_arr);

        int[] selectionResult = Selection_sort(input_arr);
        System.out.printf("Selection sort: ");
        printArray(selectionResult);

        int[] bubbleResult = Bubble_sort(input_arr);
        System.out.printf("Bubble sort: ");
        printArray(bubbleResult);

        int[] insertionResult = Insertion_sort(input_arr);
        System.out.printf("Insertion sort: ");
        printArray(insertionResult);

    }
}
