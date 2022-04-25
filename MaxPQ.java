/*
Auther: John Blue
Time: 2022/4
Platform: ATOM with atom-ide-ui, ide-java, and script
SDK: java SE 8 SDK
Object: MaxPQ (heap structure)
Reference: ...
*/

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ<Value extends Comparable<Value>> {
  // constructor
  private MaxPQ(final MaxPQ<Value> copy) {}

  // variable
  private int N;
  private int size;
  private Value[] data;

  // constructor
  public MaxPQ(int L) {
    N = 1;
    size = 1;
    for (int i = 0; i <= L; i++) {
      N *= 2;
    }
    data = (Value[]) new Comparable[N];
    //data = (Value[]) new Object[N];// not work
  }

  public void print() {
    // check
    if (isEmpty()) {
      return;
    }
    // print
    int k = 1;
    while (k < (N / 2)) {
      for (int i = k; i < k * 2; i++) {
        System.out.println(" " + data[i]);
      }
      System.out.println();
      k *= 2;
    }
  }

  public boolean isEmpty() {
      return size == 0;
  }

  public boolean isFull() {
      return size >= N;
  }

  private boolean less(int i, int j) {
      return data[i].compareTo(data[j]) < 0;
  }

  private void exch(int i, int j) {
      Value swap = data[i];
      data[i] = data[j];
      data[j] = swap;
  }



  public void EnQue(Value x) {
    // check
    if (isFull()) {
      return;
    }
    // en
    data[size] = x;
    int ck = size++;// child id
    int pk = (ck - (ck % 2)) / 2;// parent id
    while(ck > 1 && less(pk, ck)) {// we want data[pk] larger
      exch(ck, pk);
      ck /= 2;
      pk = (ck - (ck % 2)) / 2;
    }
  }

  // note: have some problem ... @@
  public Value DeMax() {
    // check
    if (isEmpty()) {
      //return - 1;
      return null;
    }
    //
    Value rt = data[1];
    // exch root and last
    exch(1, size - 1);
    // null last
    data[size - 1] = null;
    size--;
    // sink root
    int pk = 1;// parent id
    int ck = 2 * pk;// child id
    while (ck < (N / 2)) {
      // left or right node is larger
      if (ck < (size - 1)) {
        if (less(ck, ck + 1)) {
          ck++;
        }
      }
      // sorted and break
      System.out.println(size + "," + data[ck] + "," + data[pk]);
      if (less(ck, pk)) {
        break;
      }
      // exchange
      exch(pk, ck);
      print();
      // itertation
      pk = ck;
      ck = 2 * pk;
      //System.out.println(size - 1 + "," + ck + "," + pk);
    }
    // return
    return rt;
  }






    // main
    public static void main(String[] args) {
        int L = 3;// 2^3
        int dt[] = {11, 13, 16, 15, 14, 17, 12};
        MaxPQ<Integer> pq = new MaxPQ<Integer>(L);
        // enq
        for (int i = 0; i < 7; i++) {
          pq.EnQue(dt[i]);
        }
        System.out.println("MaxPQ:");
        pq.print();
        System.out.println();
        // del
        // note: have some problem ... @@
        System.out.println("Del: " + pq.DeMax());
        System.out.println("Del: " + pq.DeMax());
        pq.print();
    }

}
