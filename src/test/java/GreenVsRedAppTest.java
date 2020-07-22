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
    public void throwsExcIfTargetCellIsOutsideTheGrid() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("KeyCell is outside the grid!");
        file = getFileFromResources("exc4.txt");
        engine.run(file);
    }

    @Test
    public void throwsExc_IfInputIsWhitespace_Empty_SpecialChar() throws IOException {
        exc.expect(NullPointerException.class);
        file = getFileFromResources("exc5.txt");
        engine.run(file);
    }

    @Test
    public void unableToCreateMatrix_WithMissingElement() throws IOException {
        exc.expect(ArrayIndexOutOfBoundsException.class);
        file = getFileFromResources("exc7.txt");
        engine.run(file);
    }

    @Test
    public void unableToCreateMatrix_WithMissingRow() throws IOException {
        exc.expect(NullPointerException.class);
        file = getFileFromResources("exc8.txt");
        engine.run(file);
    }

    @Test
    public void unableToCreateMatrix_IfCellValueIsNotZeroOrOne() throws IOException {
        exc.expect(IllegalArgumentException.class);
        file = getFileFromResources("exc9.txt");
        engine.run(file);
    }

    @Test
    public void throwsExcIfParameterNIsLessOrEqualToZero() throws IOException {
        exc.expect(IllegalArgumentException.class);
        exc.expectMessage("Invalid N parameter! Unable to start the engine!");
        file = getFileFromResources("exc10.txt");
        engine.run(file);
    }

    @Test
    public void throwsExcIfParameterNIsWhitespace_Empty_SpecialChar() throws IOException {
        exc.expect(NullPointerException.class);
        file = getFileFromResources("exc11.txt");
        engine.run(file);
    }

    //Failed due to NumberFormatException! Cannot parse the input when it comes from the file,
    // but submitted from the console, it does generate output.
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