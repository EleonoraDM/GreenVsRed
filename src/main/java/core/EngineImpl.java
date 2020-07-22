package core;

import common.ExceptionMessages;
import common.Validator;
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

                if (Validator.areValidDimensions(rows, cols)) {
                    int[][] matrix = readMatrix(reader, rows, cols);

                    int[] line = parseInput(reader);
                    int x1 = line[0];
                    int y1 = line[1];
                    int n = line[2];

                    if (Validator.areValidCoordinates(x1, y1, cols, rows)) {
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
        int[] tokens = null;
        String line = reader.readLine();

        try {
            tokens = Arrays.stream(line.split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NullPointerException | NumberFormatException ex) {
            System.out.println(ex.getMessage() + ExceptionMessages.INVALID_INPUT);
        }
        return tokens;
    }

    private int[][] readMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];
        try {
            for (int r = 0; r < rows; r++) {
                String[] tokens = reader.readLine().split("");
                for (int c = 0; c < cols; c++) {
                    matrix[r][c] = Validator.isValidCellValue(tokens[c]);
                }
            }
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage() + ExceptionMessages.INVALID_INPUT);
        }
        return matrix;
    }
}
