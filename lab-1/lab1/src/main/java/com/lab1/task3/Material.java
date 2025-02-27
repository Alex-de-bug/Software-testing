package com.lab1.task3;

public enum Material {
    GOLD(0.95, "golden", true),
    SILVER(0.90, "silvery", true),
    ROCK(0.25, "rocky", false),
    WATER(0.65, "watery", false),
    IRON(0.70, "iron", true),
    SOIL(0.15, "earthy", false);
    
    private final double reflectivity;
    private final String description;
    private final boolean isMetallic;
    
    Material(double reflectivity, String description, boolean isMetallic) {
        this.reflectivity = reflectivity;
        this.description = description;
        this.isMetallic = isMetallic;
    }
    
    public double getReflectivity() {
        return reflectivity;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isMetallic() {
        return isMetallic;
    }
    
    @Override
    public String toString() {
        return description;
    }
}
