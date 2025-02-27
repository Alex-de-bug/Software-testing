package com.lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class Community {
    private String name;
    private List<Creature> members;
    private Planet habitat;
    
    public Community(String name, Planet habitat) {
        this.name = name;
        this.habitat = habitat;
        this.members = new ArrayList<>();
    }
    
    public void addMember(Creature creature) {
        members.add(creature);
        System.out.println(creature.getName() + " has joined the " + name + " community.");
    }
    
    public void removeMember(Creature creature) {
        if (members.remove(creature)) {
            System.out.println(creature.getName() + " has left the " + name + " community.");
        }
    }
    
    public int getMemberCount() {
        return members.size();
    }
    
    public List<Creature> getMembers() {
        return new ArrayList<>(members);  // Return a copy to protect encapsulation
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getHabitat() {
        return habitat;
    }

    public void setHabitat(Planet habitat) {
        this.habitat = habitat;
    }
}