package net.alephdev;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Properties {
    private static final Map<String, String> PROPERTIES = new HashMap<>();

    static {
        try (InputStream input = Properties.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            
            if (input == null) {
                throw new RuntimeException("Файл application.properties не найден в classpath");
            }
            
            java.util.Properties props = new java.util.Properties();
            props.load(input);
            
            for (String key : props.stringPropertyNames()) {
                PROPERTIES.put(key, props.getProperty(key).trim());
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    public static String getProperty(String name) {
        String value = PROPERTIES.get(name);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Свойство '" + name + "' не найдено или пустое");
        }
        return value;
    }
}



