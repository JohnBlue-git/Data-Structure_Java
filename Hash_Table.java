/*
Auther: John Blue
Time: 2022/4
Platform: ATOM with atom-ide-ui, ide-java, and script
SDK: java SE 8 SDK
Object: Hash Table (Separate Chain)
Reference: ...

Open Address Hashing:(1)Linear Probing (2)Separate Chaining

Linear Probing:
to find the next sit is called Linear Probing
to use probe's square value is called Quadratic Probing

Separate Chaining:
to use Linked List is called Separate Chaining

*/

import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Hash_Table<Value> {
    //// inner class
    class Pair {
      Integer key;
      Value val;
      Pair(Integer ky, Value vl) {
        this.key = ky;
        this.val = vl;
      }
      Pair(final Pair copy) {
        this.key = copy.key;
        this.val = copy.val;
      }
    }

    //// variable
    private static final int INIT_CAPACITY = 10;
    private LinkedList<LinkedList<Pair>> st;  // array of linked-list symbol tables
    private int size;

    //// constructor
    public Hash_Table() {
      this.st = new LinkedList<LinkedList<Pair>>();
      for (int i = 0; i < INIT_CAPACITY; i++) {
        this.st.add(new LinkedList<Pair>());
      }
      this.size = 0;
    }
    public Hash_Table(final Hash_Table<Value> copy) {
      this.size = copy.size;
      this.st = new LinkedList<LinkedList<Pair>>();
      for (int i = 0; i < INIT_CAPACITY; i++) {
        this.st.add(new LinkedList<Pair>());
      }
      Iterator<LinkedList<Pair>> it = this.st.iterator();
      for (LinkedList<Pair> PR : copy.st) {
        for (Pair pr : PR) {
          // !!! iterator.hasNext()
          // !!! iterator.next()
          it.next().add(new Pair(pr));
        }
      }
    }

    //// function

    //
    public int size() {
        return size;
    }

    //
    public boolean isEmpty() {
        return size() == 0;
    }

    //
    public void show() {
      for (LinkedList<Pair> PR : st) {
        for (Pair pr : PR) {
          System.out.println("pr.key: " + pr.key + " & " + "pr.val: " + pr.val);
        }
      }
      System.out.println();
    }

    //
    public boolean contains(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");

        return get(key) != null;
    }

    //
    public Value get(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");

        int i = hash(key);
      	for (Pair pr : st.get(i)) {
          if (pr.key == key) {
      			return pr.val;
      		}
      	}
        return null;
    }

    // hash function for keys - returns value between 0 and m-1
    private int hash(Integer key) {
      // traditional (workable with all type String Integer ... of Key)
      // return (key.hashCode() & 0x7fffffff) % m;

      // mod 10 (only for Interger Key)
      return key % 10;
    }

    //
    public void put(Integer key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) throw new IllegalArgumentException("second argument is null");

        int i = hash(key);
        LinkedList<Pair> gt = st.get(i);
        for (Pair pr : gt) {
        		if (pr.key == key) {
    			       pr.val = val;
                 return;
    		    }
	      }
        gt.add(new Pair(key, val));
	      size++;
    }

    //
    public void delete(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        LinkedList<Pair> gt = st.get(i);
        for (Pair pr : gt) {
        		if (pr.key == key) {
        			gt.remove(pr);
        			size--;
        			return;
    		   }
	      }
    }

    // return keys in symbol table as an Iterable
    /*
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }*/



    // main
    public static void main(String[] args) {
        System.out.println("put >>");
        Hash_Table<Integer> ht = new Hash_Table<Integer>();
        for (int key = 0; key < 7; key++) {
            ht.put(key, key * key);
        }
        ht.show();

        System.out.println("copy >>");
        Hash_Table<Integer> copy = new Hash_Table<Integer>(ht);
        copy.show();

        System.out.println("two sum (target = 10) >>");
        int target = 10;
        int val;
        Hash_Table<Integer> tb = new Hash_Table<Integer>();
        for (int key = 0; key < 7; key++) {
            val = ht.get(key);
            System.out.println(val);
            if (tb.contains(target - val)) {
              int rt = ht.get(tb.get(target - val)) + val;
              System.out.println("ht[" + tb.get(target - val) + "] + ht[" + key + "] = " + rt);
              // will become char(1) + char(9) = char(19)
              //System.out.println("ht[1] + ht[3] = " + ht.get(tb.get(target - val)) + val);
              break;
            }
            tb.put(val, key);
        }
    }

}
