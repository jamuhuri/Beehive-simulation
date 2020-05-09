/*
 * The Ant colony is located in a 27x27 grid
 * 
 */
package mbui.ant.simulation;

import java.util.*;

/**
 *
 * @author Mbui M Gitau
 */

public class Colony {
    ColonyNode [][] colonyGrid;
    ColonyView colonyView;
    ColonyNodeView colonyNview;
    ColonyNode cnode;
    Simulation simulation;


    Colony(ColonyView view, Simulation simulation) {
        this.simulation = simulation;
        colonyGrid = new ColonyNode [27][27];
        this.colonyView = view;




    }

    public void addColonyNode(ColonyNode nodeNum, int x, int y) {
        colonyGrid[x][y] = nodeNum;

    }
    public ColonyView getView() {
        return colonyView;
    }

    public void nextTurn(int curTurn) {
        for (int squareTotal = 0; squareTotal < 27; squareTotal++) {
            for (int m = 0; m < 27; m++) {
                colonyGrid[squareTotal][m].nextTurn(curTurn);
            }
        }

    }

    //Create colony in a 27x27grid
    public void initColony() {
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                colonyNview = new ColonyNodeView();
                cnode = new ColonyNode(colonyNview, i, j);
                cnode.setColony(this);
                colonyView.addColonyNodeView(colonyNview, i, j);
                addColonyNode(cnode, i, j);
                colonyNview.setID(i + "," + j);

                if (i == 13 && j == 13) {
                    Queen q = new Queen(cnode);
                    cnode.setFood(1000);
                    cnode.addAnt(q);

                    for (int k = 0; k < 10; k++) {
                        q.hatch(new Soldier(cnode));
                    }
                    for (int l = 0; l < 50; l++) {
                        q.hatch(new Forager(cnode));
                    }
                    for (int m = 0; m < 4; m++) {
                        q.hatch(new Scout(cnode));
                    }
                }
                if ((i == 12 && j == 12) || (i == 12 && j == 13) || 
                        (i == 12 && j == 14) || (i == 13 && j == 12) || 
                        (i == 13 && j == 13) || (i == 13 && j == 14) || 
                        (i == 14 && j == 12) || (i == 14 && j == 13) || 
                        (i == 14 && j == 14)) {
                    cnode.setVisible(true);
                }


            }
        }
    }

    public ArrayList<ColonyNode> getAdjacentNodes(ColonyNode node) {
        int x = node.getX();
        int y = node.getY();
        ArrayList<ColonyNode> adjacentNodes;
        adjacentNodes = new ArrayList<ColonyNode>();

        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx != 0 || dy != 0) {
                    try {
                        adjacentNodes.add(colonyGrid[x + dx][y + dy]);
                    } catch (Exception e) {}
                }
            }
        }
        return adjacentNodes;
    }


    
}
