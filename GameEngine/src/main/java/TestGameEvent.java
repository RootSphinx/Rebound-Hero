import org.sphinx.sphinxengine.engine.EventSystem;
import org.sphinx.sphinxengine.engine.GameEvent;
import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.ui.Dialog;
import org.sphinx.sphinxengine.util.Utils;

public class TestGameEvent implements GameEvent {
    public TestGameEvent(){
        super();
    }
    public static void wait(GameObject gameObject, String[] args){
        Dialog dialog = (Dialog) gameObject;
        dialog.setFreeze(true);
        new Thread(()->{
            while (true){
                if (EventSystem.getMouseButton1()){
                    dialog.setFreeze(false);
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public static void shake(GameObject gameObject,String[] args){
        Dialog dialog = (Dialog) gameObject;
        Utils.createShaker(dialog.lable.transform,10,0.15);
    }
    public static void stop(GameObject gameObject,String[]args){
        Dialog dialog = (Dialog) gameObject;
        dialog.getCanvas().setActive(false);
    }
}
