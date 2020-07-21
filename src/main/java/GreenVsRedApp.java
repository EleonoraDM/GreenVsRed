import common.ExceptionMessages;
import core.Engine;
import core.EngineImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GreenVsRedApp {

    public static void main(String[] args) throws IOException {

        GreenVsRedApp game = new GreenVsRedApp();
        File file = game.getFileFromResources("input1.txt");
        Engine engine = new EngineImpl();
        System.out.println(engine.run(file));

    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException(ExceptionMessages.MISSING_FILE);
        } else {
            return new File(resource.getFile());
        }
    }
}







