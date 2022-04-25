/*
Auther: John Blue
Time: 2022/4
Platform: ATOM with atom-ide-ui, ide-java, and script
SDK: java SE 8 SDK
Object: Union Find
Reference: ...
*/

public class UF {
    //// variable
    // quick_find variable
    //private int[] id;    // id[i] = component identifier of i
    //private int count;   // number of components
    // quick_union / weighted union variable
    private int[] parent;  // parent[i] = parent of i
    private int[] size;    // (only for weighted UF) size[i] = number of elements in subtree rooted at i
    private int count;     // number of components

    //// constructor
    public UF(int n) {
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    //// function
    public int count() {
        return count;
    }

    public void show() {
        for (int i = 0; i < count; i++) {
            System.out.print(parent[i]);
        }
        System.out.println();
    }


    //// find and union

    // quick_find
    /*
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = id[p];   // needed for correctness
        int qID = id[q];   // to reduce the number of array accesses

        // p and q are already in the same component
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }
    */

    // quick_union
    /*
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
    */

    // weighted_union
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
          return;
        }

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }



    // main
    public static void main(String[] args) {
      // setting
      int N = 9;
      UF uf = new UF(N);
      System.out.print("uf: ");
      uf.show();
      // union
      int half = N / 2;
      for (int i = 1; i < half; i++) {
        if (! uf.connected(uf.find(i - 1), uf.find(i))) {
          uf.union(i - 1, i);
        }
        System.out.print("uf: ");
        uf.show();
      }
      for (int i = half + 1; i < N; i++) {
        if (! uf.connected(uf.find(i - 1), uf.find(i))) {
          uf.union(i - 1, i);
        }
        System.out.print("uf: ");
        uf.show();
      }
      System.out.println("connected(0, N - 1): " + uf.connected(0, N - 1));
      System.out.println("last connecting ...");
      uf.union(0, half + 1);
      System.out.println("connected(0, N - 1): " + uf.connected(0, N - 1));
    }
}
