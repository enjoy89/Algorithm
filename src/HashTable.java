import java.util.LinkedList;
import java.util.Scanner;

/**
 * "key" 값만 저장하는 간단한 Hash table 구현
 */
public class HashTable {
    private LinkedList<Entry>[] table;
    private int numKeys;
    private static final int CAPACITY = 10;
    private static final double LOAD_THRESHOLD = 3.0;

    private static class Entry {
        private String key;

        public Entry(String key) {
            this.key = key;

        }

        public String getKey() {
            return key;
        }

        public String toString() {
            return key.toString();
        }
    }

    public HashTable() {
        table = new LinkedList[CAPACITY];
    }

    // 해당 key 값이 해쉬테이블에 존재하는지 확인하는 메소드
    // 해당 key를 찾아 리턴
    public String searchKey(String key) {
        int index = getHashCode(key);

        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null;
        }
        for (Entry nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.getKey();
            }
        }
        return null;
    }

    // hash table에 새로운 key 값을 저장하는 메소드
    public String put(String key) {
        int index = getHashCode(key);

        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            table[index] = new LinkedList<Entry>();
        }
        table[index].addFirst(new Entry(key));
        numKeys++;
        return null;
    }

    public int getHashCode(String key) {
        return key.hashCode() % table.length;
    }

    // hash table 출력 메소드
    public void printHashTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                System.out.print("load[" + i + "] = "); // 엔트리의 방 번호
                System.out.print(table[i].size() + "/" + numKeys + ", ");  // 각각의 방 안에 존재하는 리스트의 길이와 지금까지 입력된 key의 총 수.

            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashTable hashTable = new HashTable();

        while (true) {
            System.out.print("(QUIT: 동작 종료| Key 값을 입력해주세요: ");
            String k = input.next();

            if (k.equals(hashTable.searchKey(k))) {
                System.out.print("이미 존해하는 값입니다. 다시 입력해주세요: ");
            } else if (k.equals("QUIT")) {
                hashTable.printHashTable();
                break;
            } else {
                hashTable.put(k);
            }
        }
    }
}
