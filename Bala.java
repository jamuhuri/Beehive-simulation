/*
 * Bala ant is resposible for fighting
 * Bala inherits the ant properties
 */
package mbui.ant.simulation;

import java.util.*;

/**
 *
 * @author Mbui M Gitau
 */

public class Bala extends Ant {
    
    //Constructor to initialize variables
    Bala(ColonyNode node) {
        antLocation = node;
        antLastMove = -1;

    }

    Bala() {

    }

    public void nextTurn(int curTurn) {
        // check if ants in the list have already taken their turn
        if (antLastMove == curTurn)
            return;

        //check if age is atleast 1 year
        if ((curTurn - antProduce) > 10 * 365) {
            die();
            return;
        }

        antLastMove = curTurn;
        ArrayList<Ant> nonBala = antLocation.getNonBalaAnts();
        // if ant has not taken turn, and nonBala ants are present, fight friendly ants
        if (nonBala.size() > 0) {
            fight();
        }

        else {
            Random moveGen = new Random();
            ArrayList<ColonyNode> adjacentList = antLocation.getAdjacentNodes();
            ColonyNode destination;
            // Set destination to a random adjacent visible node
            destination = adjacentList.get(moveGen.nextInt(adjacentList.size()));
            movement(destination);
        }
    }

    public void movement(ColonyNode node) {
        antLocation.removeAnt(this);
        antLocation = node;
        antLocation.addAnt(this);

    }

    public void fight() {
        ArrayList<Ant> nonBala = antLocation.getNonBalaAnts();
        Random kill = new Random();
        int half = kill.nextInt(2);
        if (half == 0) {
            nonBala.get(0).die();
        }
    }

    
}
