package main;

import java.util.ArrayList;
import java.util.List;

public class N_QueensMonteCarlo {
    private int[] x; // Array will hold the column positions of queens, 1-based index
    private int n;  // Size of the board and number of queens
    int numNodes; // number of nodes aka columns visited

    public N_QueensMonteCarlo(int n) {
        this.n = n;
        this.x = new int[n + 1]; // Creating an array from 0 to n (0th index will be unused)
        this.numNodes = 1;
        N_queensB(1, n);
    }

    private void N_queensB(int k, int n) {

        List<Integer> promisingState =  new ArrayList<>(); // list of promising states
        for (int i = 1; i <= n; i++) { // Iterate over all columns from 1 to n
            numNodes++;
            if (promising(k, i)) {
                x[k] = i; // Place queen at position [k, i]
                if(k==n) {
                    printSolution();
                } else {
                    N_queensB(k + 1, n); // Move to the next row
                }

            }
        }
    }

    private boolean promising(int k, int i) {
        for (int j = 1; j < k; j++) { // Check all previous rows for conflicts
            if (x[j] == i || // Same column
                    Math.abs(x[j] - i) == Math.abs(j - k)) { // Same diagonal
                return false;
            }
        }
        return true;
    }

    private void printSolution() {
        System.out.println("One of the solutions:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(numNodes);
    }

    private int run_MonteCarlo() {

        return numNodes;
    }

    public static void main(String[] args) {
        int n = 4; // Solving 8-Queens problem
        N_QueensMonteCarlo solver = new N_QueensMonteCarlo(n);

    }
}
