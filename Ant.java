/*
 * Ant class is the super class inherited by all the other classes in the package
 * 
 */
package mbui.ant.simulation;
import java.util.*;

/**
 *
 * @author Mbui M Gitau
 */
 
class Ant {
    
    int antID;
    int antLifeSpan;
    boolean whenAlive;
    ColonyNode antLocation;
    int antLastMove;
    int antProduce;

    // constructor to initialize the class variables
    public Ant(ColonyNode node) {
        antID = 0;
        antLifeSpan = 0;
        whenAlive = true;
        antLocation = node;
        antLastMove = 0;

    }

    //default constructor
    public Ant() {
        
    }

    //movement method sets colony node to new location
    public void movement(ColonyNode newLocation) {
        antLocation.removeAnt(this);
        antLocation = newLocation;
        antLocation.addAnt(this);
        
    }

    public void nextTurn(int curTurn) {

    }

    public void die() {
        antLocation.removeAnt(this);
    }

    public void setBirthday(int curTurn) {
        antProduce = curTurn;
    }

    public void setID(int antId) {
        this.antID = antId;
    }
}

