package com.example.illegalaliens.models.projectiles;

/**
 * Created by Emil on 2017-04-28.
 */
public interface ProjectileObserver {
    void actOnProjectileChange(Projectile projectile, String change);
}
