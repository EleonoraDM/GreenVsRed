package core;

import common.ExceptionMessages;
import services.ColourCounter;
import services.ColourCounterImpl;

import java.io.*;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private final ColourCounter counter = new ColourCounterImpl();

    @Override
    public int run(File file) throws IOException {
        int result = 0;

        if (file != null) {
            try (FileReader fr = new FileReader(file);
                 BufferedReader reader = new BufferedReader(fr)) {

                int[] dimensions = parseInput(reader);
                int cols = dimensions[0];//x
                int rows = dimensions[1];//y

                if (areValidDimensions(rows, cols)) {
                    int[][] matrix = readMatrix(reader, rows, cols);

                    int[] line = parseInput(reader);
                    int x1 = line[0];
                    int y1 = line[1];
                    int n = line[2];

                    if (areValidCoordinates(x1, y1, cols, rows)) {
                        result = counter.countGreenis(cols, rows, matrix, y1, x1, n);
                    } else {
                        throw new IllegalArgumentException(ExceptionMessages.INVALID_COORDINATES);
                    }
                } else {
                    throw new IllegalArgumentException(ExceptionMessages.INVALID_DIMENSIONS);
                }
            }
        }
        return result;
    }

    private int[] parseInput(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private boolean areValidDimensions(int rows, int cols) {
        return (cols >= 2 && cols <= rows) && rows < 1000;
    }

    private int[][] readMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return matrix;
    }

    private boolean areValidCoordinates(int x1, int y1, int cols, int rows) {
        return (x1 >= 0 && x1 < cols) && (y1 >= 0 && y1 < rows);
    }
}
