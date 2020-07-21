package core;

import java.io.File;
import java.io.IOException;

public interface Engine {

    int run(File file) throws IOException;
}
