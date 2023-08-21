package org.sphinx.sphinxengine.engine;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String getFileContent(String path){
        try {
            InputStream stream = Utils.class.getResourceAsStream(path);
            byte[] bytes = stream.readAllBytes();
            return new String(bytes);
        }
        catch (NullPointerException | IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
