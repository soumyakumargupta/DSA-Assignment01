//  Tree Product problem 1 solution
import java.util.Scanner;

public class Main {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int H = scanner.nextInt();
            if (H == 0)
                break;

            int totalNodes = (1 << H) - 1; // 2^H - 1
            int[] values = new int[totalNodes + 1];
            for (int i = 1; i <= totalNodes; i++) {
                values[i] = scanner.nextInt();
            }

            System.out.println(computeP1(H, values));
        }
        scanner.close();
    }

    private static long computeP1(int H, int[] values) {
        return computeP(1, H, values);
    }

    private static long computeP(int nodeIndex, int H, int[] values) {
        // If nodeIndex exceeds the number of nodes in a complete tree of height H
        if (nodeIndex >= (1 << H)) {
            return 0; // Non-existent node
        }

        int leftChild = nodeIndex * 2;
        int rightChild = nodeIndex * 2 + 1;

        // Leaf node check
        if (leftChild >= (1 << H)) {
            return values[nodeIndex];
        }

        // Compute values for children
        long PL = computeP(leftChild, H, values);
        long PR = computeP(rightChild, H, values);

        long maxProduct = Math.max(((values[nodeIndex] * PL) % MOD), ((values[nodeIndex] * PR) % MOD));
        return maxProduct;
    }
}
