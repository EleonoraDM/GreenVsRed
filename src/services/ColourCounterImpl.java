package services;

import factories.GenerationFactory;
import factories.GenerationFactoryImpl;

public class ColourCounterImpl implements ColourCounter {
    private GenerationFactory factory;

    public ColourCounterImpl() {
        this.factory = new GenerationFactoryImpl();
    }

    @Override
    public int countGreenis(int x, int y,
                            int[][] matrix,
                            int cellY, int cellX,
                            int n) {

        int targetValue = matrix[cellY][cellX];
        int counter = 0;

        if (getCellColor(targetValue)) {
            counter++;
        }
        while (n-- > 0) {
            matrix = factory.createNextGeneration(x, y, matrix);

            if (getCellColor(matrix[cellY][cellX])) {
                counter++;
            }
        }
        return counter;
    }

    private boolean getCellColor(int targetValue) {
        return targetValue == 1;
    }
}
