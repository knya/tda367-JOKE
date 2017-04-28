package models;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;
import utilities.ProjectileObserver;
import utilities.Radar;
import utilities.UpdateObserver;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileModel implements UpdateObserver{
    private Array<Projectile> projectiles;

    private Radar radar;

    public ProjectileModel(Radar radar){
        projectiles = new Array<Projectile>();
        this.radar = radar;
        
    }

    @Override
    public void update(float deltaTime) {
        for (Projectile projectile : projectiles){
            move(projectile);
            if (checkIfHitEnemies(projectile)){
                damageEnemies(projectile);
            }
            checkForRemoval(projectile);
        }

    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public void move(Projectile p){
            p.setSpritePosition(p.getNewPosition());
    }

    public boolean checkIfHitEnemies(Projectile projectile) {
        Array<Enemy> enemies = scan(projectile);
        return (enemies.size != 0);
    }

    public Array<Enemy> scan(Projectile projectile){
        Array<Enemy> enemies = radar.scan(projectile.getPosition(), projectile.getRadius()); //hardcoded
        return enemies;
    }

    public void damageEnemies(Projectile projectile){
        Array<Enemy> enemies = scan(projectile);
        for (Enemy enemy: enemies){
        enemy.hurt(projectile.getDamage());
        }
    }


    public boolean ifOutOfBounds(Projectile p){
        return (p.getPosition().getY() > 720 && p.getPosition().getY() < 0 && p.getPosition().getX() > 1280 && p.getPosition().getX() < 0);
    }

    public void removeProjectileAfterHit(Projectile p){
        projectiles.removeValue(p, false);
        p = null;
    }



    public void checkForRemoval(Projectile p){
            if (ifOutOfBounds(p) || checkIfHitEnemies(p)){
                notifyObservers(p, "remove");
            }
    }

    public void removeProjectile(Projectile p){
        projectiles.removeValue(p, false);
        p = null;
    }

    private Array<ProjectileObserver> observers = new Array<ProjectileObserver>();

    public void addObserver(ProjectileObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProjectileObserver observer) {
        observers.removeValue(observer, false);
    }

    private void notifyObservers(Projectile projectile, String change) {
        for (ProjectileObserver observer : observers)
            observer.actOnProjectileChange(projectile, change);
    }


}
