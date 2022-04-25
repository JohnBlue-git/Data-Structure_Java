/*
Auther: John Blue
Time: 2022/4
Platform: ATOM with atom-ide-ui, ide-java, and script
SDK: java SE 8 SDK
Object: Heap Sort
Reference: ...
*/

public class Heap {
    // This class should not be instantiated.
    private Heap() { }

    // sort function
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n / 2; k >= 1; k--) {
          sink(pq, k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

   // private function
    private static void sink(Comparable[] pq, int k, int n) {
        int j;
        while (2 * k <= n) {
            j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
              j++;
            }
            if (!less(pq, k, j)) {
              break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }



    // main
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        show(a);
    }
}
