import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.Sprite;
import org.sphinx.sphinxengine.engine.Texture;

public class Player extends GameObject {
    Sprite sprite = new Sprite("/图片包/image0.png");
    @Override
    public void start(){

        System.out.println("player.start()");
    }

    @Override
    public void update(){
        //System.out.println("player.update()");
    }

    @Override
    public void enable() {
        System.out.println("player.enable()");
    }

    @Override
    public void disable() {

    }
}
