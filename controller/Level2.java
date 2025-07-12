package game.controller;

import game.model.*;
import game.view.StartSystemView;
import game.view.SystemView;

public class Level2 {
    private GameState gameState;

    public Level2(GameState gameState) {
        this.gameState = gameState;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        new SquarePort(generalSystem3, PortType.INPUT);
        new TrianglePort(generalSystem3, PortType.INPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new TrianglePort(generalSystem3, PortType.OUTPUT);

        SystemModel generalSystem = new SystemModel(600, 600);
        new SquarePort(generalSystem, PortType.INPUT);
        new TrianglePort(generalSystem, PortType.INPUT);
        new SquarePort(generalSystem, PortType.OUTPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);

        SystemModel generalSystem2 = new SystemModel(1000, 600);
        new SquarePort(generalSystem2, PortType.INPUT);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new SquarePort(generalSystem2, PortType.OUTPUT);
        new TrianglePort(generalSystem2, PortType.OUTPUT);

        SystemModel generalSystem4 = new SystemModel(1300, 200);
        new SquarePort(generalSystem4, PortType.INPUT);
        new TrianglePort(generalSystem4, PortType.INPUT);
        new SquarePort(generalSystem4, PortType.OUTPUT);
        new TrianglePort(generalSystem4, PortType.OUTPUT);

        gameState.addSystem(generalSystem3);
        gameState.addSystem(generalSystem);
        gameState.addSystem(generalSystem2);
        gameState.addSystem(generalSystem4);
    }
}
