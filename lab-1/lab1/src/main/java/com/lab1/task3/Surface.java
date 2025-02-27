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
        return "A " + material.getDescription() + " surface that extends for " + 
                String.format("%.2f", area) + " square units";
    }
    
    public boolean isShiny() {
        return material.getReflectivity() > 0.7;
    }
    
    public String describeExtent() {
        StringBuilder description = new StringBuilder();
        description.append("A continuous ").append(material.getDescription())
                   .append(" surface extending in all directions to the horizon. ");
        
        if (material == Material.GOLD) {
            description.append("It glitters with unmatched brilliance.");
        }
        
        return description.toString();
    }
    
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