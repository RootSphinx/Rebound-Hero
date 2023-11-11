import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;
import org.sphinx.engine.Vector2D;

public class GameTestScene implements Scene {
    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        BackGround1 backGround1 = new BackGround1();

        //BackGround2 backGround2 = new BackGround2();
        Player player = new Player();
        FloorCheck floorCheck = new FloorCheck(player);

/*        for (int i = 0; i < 20; i++) {
            LittlePoint.littlePoints.add(new LittlePoint(i));
        }*/


        gameCamera.target = player;
    }
}
