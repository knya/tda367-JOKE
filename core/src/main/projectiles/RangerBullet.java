package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

/**
 * Created by Emil on 2017-04-17.
 */
public class RangerBullet extends Projectile {
    private static final float DAMAGE = 15;
    private static final float SPEED = 50;
    private static final boolean AREAOFEFFECT = false;
    private static final Texture TEXTURE = new Texture("missile.png");

    public RangerBullet(Node DIRECTION, Node POSITION){
        super(DAMAGE, SPEED, AREAOFEFFECT, TEXTURE, DIRECTION, POSITION);
    }

}