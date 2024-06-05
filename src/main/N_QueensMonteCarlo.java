/*
* Marcos Garcia
*
* Using monte carlo algorithm to estimate efficiency
* of using backtracking algorithm to solve the n-queens problem.
*/
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N_QueensMonteCarlo {
    private int[] x;
    private int n;
    int numNodes;

    public N_QueensMonteCarlo(int n) {
        this.n = n;
        this.x = new int[n + 1];
        this.numNodes = 0;
        N_queensB(1, n);
    }

    private void N_queensB(int column, int n) {
        int m = 1;
        int mprod = 1;
        int i = 1;

        while (m != 0 && i <= n) {
            mprod *= m;
            numNodes += mprod * n;
            List<Integer> promChildren = new ArrayList<>();
            m = 0;
            for (int j = 1; j <= n; j++) {
                if (promising(i, j)) {
                    m++;
                    promChildren.add(j);
                }
            }
            if (!promChildren.isEmpty()) {
                Collections.shuffle(promChildren);
                x[i] = promChildren.get(0);
            }
            i++;
        }
    }

    private boolean promising(int column, int i) {
        for (int j = 1; j < column; j++) {
            if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - column)) {
                return false;
            }
        }
        return true;
    }

    public void MonteCarlo(int count) {
        double total = 0;
        for (int i = 1; i <= count; i++) {
            x = new int[n+1];
            numNodes = 0;
            N_queensB(1,n);
            total += numNodes;
            System.out.println("Test: " + i + ".) nodes visited: " + numNodes);
        }
        System.out.println("Average nodes visited: " + total / count);
    }

    public static void main(String[] args) {
        int n = 12;
        int count = 20;
        N_QueensMonteCarlo solver = new N_QueensMonteCarlo(n);
        solver.MonteCarlo(count);

    }
}
