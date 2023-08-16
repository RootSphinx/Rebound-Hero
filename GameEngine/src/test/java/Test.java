import org.sphinx.sjge.engine.GameEngine;

public class Test {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.setWindowInfo(1400,800,"你好");
        gameEngine.run();
    }
}
