package com.lab1.task3;

public class Surface {
    private Material material;
    private double area;
    private boolean isVisible;
    
    public Surface(Material material, double area) {
        this.material = material;
        this.area = area;
        this.isVisible = true;
    }
    
    public double calculateShine(double lightIntensity) {
        return lightIntensity * material.getReflectivity();
    }
    
    public String getDescription() {
        return "A " + material.getDescription() + " surface that extends for " + area + " square units";
    }
    
    public boolean isShiny() {
        return material.getReflectivity() > 0.7;
    }
    
    // Getters and setters
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}