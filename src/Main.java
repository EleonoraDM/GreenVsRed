import factories.GenerationFactory;
import factories.GenerationFactoryImpl;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static GenerationFactory factory = new GenerationFactoryImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = dimensions[0];//TODO - cols !!!
        int y = dimensions[1];//TODO - rows !!!

        int[][] matrix = new int[y][x];

        for (int i = 0; i < y; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        int[] line = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int x1 = line[0];
        int y1 = line[1];
        int n = line[2];

        int targetValue = matrix[y1][x1];
        int counter = 0;

        if (getTargetColour(targetValue)) {
            counter++;
        }

        while (n-- > 0) {

            matrix = factory.createNextGeneration(x, y, matrix);

            if (getTargetColour(matrix[y1][x1])) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static boolean getTargetColour(int targetValue) {
        return targetValue == 1;
    }
}





