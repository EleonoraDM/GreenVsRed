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

                int[] dimensions = Arrays.stream(reader.readLine().split(", "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int x = dimensions[0];//TODO - cols !!!
                int y = dimensions[1];//TODO - rows !!!

                if (x > 2 && x <= y && y < 1000) {
                    int[][] matrix = new int[y][x];

                    for (int i = 0; i < y; i++) {
                        matrix[i] = Arrays.stream(reader.readLine().split(""))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                    }
                    int[] line = Arrays.stream(reader.readLine().split(", "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    int x1 = line[0];
                    int y1 = line[1];
                    int n = line[2];

                    if ((x1 >= 0 && x1 < x) && (y1 >= 0 && y1 < y)) {
                        result = counter.countGreenis(x, y, matrix, y1, x1, n);
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
}
