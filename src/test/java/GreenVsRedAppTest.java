import core.Engine;
import core.EngineImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GreenVsRedAppTest {
    private File file;
    private Engine engine;

    @Rule
    public ExpectedException exc = ExpectedException.none();

    @Before
    public void setUp() {
        engine = new EngineImpl();
    }

    @Test
    public void invalidGridDimensions_ColsGreaterThanRows() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("Invalid grid dimensions!");
        file = getFileFromResources("exc1.txt");
        engine.run(file);
    }

    @Test
    public void invalidGridDimensions_RowsGreaterThanOrEqualTo1000() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("Invalid grid dimensions!");
        file = getFileFromResources("exc2.txt");
        engine.run(file);
    }

    @Test
    public void invalidGridDimensions_WithAnyOfTheDimensionsLessThan2() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("Invalid grid dimensions!");
        file = getFileFromResources("exc3.txt");
        engine.run(file);
    }

    @Test
    public void throwsExceptionIfTargetCellIsOutsideTheGrid() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("Targeted cell is outside the grid!");
        file = getFileFromResources("exc4.txt");
        engine.run(file);
    }

    //Failed due to NumberFormatException! Cannot parse the input when it comes from the file.
    // But when it is submitted from the console, it does generate output.
    @Test
    public void extractsResultWithMaxGridDimensions() throws IOException {
        file = getFileFromResources("max_dimension.txt");
        System.out.println(engine.run(file));
    }

    @Test
    public void extractsResultWithMinGridDimensions() throws IOException {
        file = getFileFromResources("min_dimension.txt");
        System.out.println(engine.run(file));
    }





    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }


}