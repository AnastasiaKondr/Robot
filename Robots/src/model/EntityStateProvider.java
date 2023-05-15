package model;

public class EntityStateProvider implements GameStateProvider {
    private GameState gameState;

    public EntityStateProvider(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public Target getCurrentTarget() {
        return gameState.getTarget();
    }
    @Override
    public Robot getCurrentRobot() {
        return gameState.getRobot();
    }
}
