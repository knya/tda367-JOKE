package waves;

import com.badlogic.gdx.utils.Array;
import enemies.*;

/**
 * Created by Emil on 2017-05-21.
 */
public class LevelCreator {

    //Array containing all the levels created in constructor
    Array<Level> levels = new Array<Level>();


    Array<LevelHelperObject> L1 = new Array<LevelHelperObject>();
    Array<LevelHelperObject> L2 = new Array<LevelHelperObject>();
    Array<LevelHelperObject> L3 = new Array<LevelHelperObject>();
    Array<LevelHelperObject> L4 = new Array<LevelHelperObject>();
    Array<LevelHelperObject> L5 = new Array<LevelHelperObject>();
    Array<LevelHelperObject> L6 = new Array<LevelHelperObject>();


    //Aliens available to use
    Alien alien = new Alien();
    HighAlien highAlien = new HighAlien();
    SneakyAlien sneakyAlien = new SneakyAlien();
    AlienWithHelmet alienWithHelmet = new AlienWithHelmet();
    ToughAlien toughAlien = new ToughAlien();

    public LevelCreator(){
        //level 1
        L1.add(new LevelHelperObject(alien, 3));
        //level 2
        L2.add(new LevelHelperObject(alien,5));
        L2.add(new LevelHelperObject(sneakyAlien,3));
        //level 3
        L3.add(new LevelHelperObject(sneakyAlien,3));
        L3.add(new LevelHelperObject(alien,3));
        L3.add(new LevelHelperObject(alienWithHelmet,3));

        //level 4
        L4.add(new LevelHelperObject(sneakyAlien,5));
        L4.add(new LevelHelperObject(alien,5));
        L4.add(new LevelHelperObject(alienWithHelmet,5));
        L4.add(new LevelHelperObject(highAlien,5));
        //level 5
        L5.add(new LevelHelperObject(sneakyAlien,3));
        L5.add(new LevelHelperObject(alien,3));
        L5.add(new LevelHelperObject(alienWithHelmet,3));
        L5.add(new LevelHelperObject(highAlien,5));
        L5.add(new LevelHelperObject(toughAlien,5));

        //level 6
        L6.add(new LevelHelperObject(sneakyAlien,5));
        L6.add(new LevelHelperObject(alien,5));
        L6.add(new LevelHelperObject(alienWithHelmet,5));
        L6.add(new LevelHelperObject(highAlien,5));
        L6.add(new LevelHelperObject(toughAlien,10));

        levels.add(new Level(3,L1,true));
        levels.add(new Level(3,L2,true));
        levels.add(new Level(3,L3,true));
        levels.add(new Level(3,L4,true));
        levels.add(new Level(3,L5,true));
        levels.add(new Level(50,L6,true));

        levels.reverse();

    }


    public Level getLevel(int i){
        return levels.get(i); //Todo: implement index out of bounds
    }
    public Array<Level> getAllLevels(){
        return levels;
    }


}