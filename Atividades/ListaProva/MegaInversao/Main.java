import java.util.*;

class Fenwick {
    private long[] bit;
    private int n;

    public Fenwick(int n) {
        this.n = n;
        bit = new long[n + 1];
    }

    public void update(int idx, long val) {
        while (idx <= n) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    public long query(int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    public long queryRange(int l, int r) {
        return query(r) - query(l - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // Fenwick para contar quantos maiores vieram antes
        Fenwick fenwickLeft = new Fenwick(n);
        long[] maioresAntes = new long[n];

        for (int i = 0; i < n; i++) {
            // quantos já inseridos são > arr[i]
            maioresAntes[i] = fenwickLeft.queryRange(arr[i] + 1, n);
            fenwickLeft.update(arr[i], 1);
        }

        // Fenwick para contar quantos menores virão depois
        Fenwick fenwickRight = new Fenwick(n);
        long[] menoresDepois = new long[n];

        for (int i = n - 1; i >= 0; i--) {
            // quantos já inseridos (à direita) são < arr[i]
            menoresDepois[i] = fenwickRight.query(arr[i] - 1);
            fenwickRight.update(arr[i], 1);
        }

        // Agora calculamos triplos
        long triplos = 0;
        for (int i = 0; i < n; i++) {
            triplos += maioresAntes[i] * menoresDepois[i];
        }

        System.out.println(triplos);
    }
}
