package net.alephdev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Properties {
    private static final Map<String, String> properties = new HashMap<>();

    static {
        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(Files.newInputStream(Paths.get("application.properties")));
            for (String key : prop.stringPropertyNames()) {
                properties.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("Не удалось загрузить файл свойств: " + e.getMessage());
        }
    }

    public static String getProperty(String name) {
        return properties.get(name);
    }
}
