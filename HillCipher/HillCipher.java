import java.util.Scanner;

public class HillCipher {
    private static int[][] keyMatrix;
    private static int matrixSize;

    // Function to get key matrix from key string
    public static void getKeyMatrix(String key, int size) {
        matrixSize = size;
        keyMatrix = new int[matrixSize][matrixSize];
        int k = 0;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = (key.charAt(k)) % 65;
                k++;
            }
        }
    }

    // Function to encrypt plaintext
    public static String encrypt(String plaintext) {
        int[] plaintextVector = new int[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            plaintextVector[i] = (plaintext.charAt(i)) % 65;
        }

        int[] cipherVector = new int[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            cipherVector[i] = 0;
            for (int j = 0; j < matrixSize; j++) {
                cipherVector[i] += keyMatrix[i][j] * plaintextVector[j];
            }
            cipherVector[i] = cipherVector[i] % 26;
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < matrixSize; i++) {
            cipherText.append((char) (cipherVector[i] + 65));
        }

        return cipherText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter key string (must be a perfect square in length, e.g., 4, 9, 16...):");
        String key = scanner.next().toUpperCase();
        int size = (int) Math.sqrt(key.length());
        if (size * size != key.length()) {
            System.out.println("Invalid key length! Must be a perfect square.");
            return;
        }

        getKeyMatrix(key, size);
        
        System.out.println("Enter plaintext (length must be " + size + " characters):");
        String plaintext = scanner.next().toUpperCase();
        if (plaintext.length() != size) {
            System.out.println("Invalid plaintext length!");
            return;
        }

        String ciphertext = encrypt(plaintext);
        System.out.println("Encrypted text: " + ciphertext);
    }
}
