package com.lab1.task3;

public class Creature {
    private String name;
    private double height;
    private double weight;
    private Position position;
    private Eyes eyes;
    private Mouth mouth;
    private Throat throat;
    
    public Creature(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.position = new Position(0, 0, 0);
        this.eyes = new Eyes();
        this.mouth = new Mouth();
        this.throat = new Throat();
    }
    
    public void jump() {
        System.out.println(name + " jumped up lightly like a ballet dancer.");
    }
    
    public void lookAround() {
        eyes.scan();
        System.out.println(name + " is looking around.");
    }
    
    public void move(double x, double y, double z) {
        position.setX(position.getX() + x);
        position.setY(position.getY() + y);
        position.setZ(position.getZ() + z);
        System.out.println(name + " moved to position: " + position);
    }
    
    // Nested classes for body parts
    public class Eyes {
        private boolean isOpen;
        
        public Eyes() {
            this.isOpen = true;
        }
        
        public void open() {
            isOpen = true;
            System.out.println(name + " opened their eyes.");
        }
        
        public void close() {
            isOpen = false;
            System.out.println(name + " closed their eyes.");
        }
        
        public void scan() {
            if (isOpen) {
                System.out.println(name + " is scanning the surroundings with their eyes.");
            } else {
                System.out.println(name + " can't see with closed eyes.");
            }
        }
    }
    
    public class Mouth {
        private boolean isOpen;
        
        public Mouth() {
            this.isOpen = false;
        }
        
        public void open() {
            isOpen = true;
            System.out.println(name + " opened their mouth.");
        }
        
        public void close() {
            isOpen = false;
            System.out.println(name + " closed their mouth.");
        }
        
        public void speak(String words) {
            if (isOpen) {
                System.out.println(name + " says: \"" + words + "\"");
            } else {
                System.out.println(name + " tries to speak with closed mouth.");
            }
        }
    }
    
    public class Throat {
        public void swallow() {
            System.out.println(name + " swallows.");
        }
        
        public void makeSound(String sound) {
            System.out.println(name + " makes a sound: " + sound);
        }
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Eyes getEyes() {
        return eyes;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public Throat getThroat() {
        return throat;
    }
}