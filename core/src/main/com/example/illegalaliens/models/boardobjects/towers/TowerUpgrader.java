package com.example.illegalaliens.models.boardobjects.towers;

/**
 * Created by Emil on 2017-05-03.
 */
public class TowerUpgrader {
    /**
     * This class handles all permament tower upgrades
     */


    //Variables to multiply the current value with
    private float radiusMultiplier;
    private float damageMultiplier;
    private float cooldownMultiplier;

    //Cost multipliers
    private float radiusUpgradeCostMultiplier;
    private float damageUpgradeCostMultiplier;
    private float cooldownUpgradeCostMultiplier;



    public TowerUpgrader(){
        radiusMultiplier = 1.3f;
        damageMultiplier = 1.3f;
        cooldownMultiplier = 0.9f;

        radiusUpgradeCostMultiplier = 1.5f;
        damageUpgradeCostMultiplier = 1.4f;
        cooldownUpgradeCostMultiplier = 1.45f;

    }

    public void upgradeRadius(Tower tower){
        tower.setRadius(tower.getRadius() * radiusMultiplier);
        tower.setValue(tower.getValue() * radiusUpgradeCostMultiplier);
    }

    public void upgradeDamage(Tower tower){
        tower.setDamage(tower.getDamage() * damageMultiplier);
        tower.setValue(tower.getValue() * damageUpgradeCostMultiplier);
    }


    public void reduceCooldown(Tower tower){
        tower.getCooldownObject().setCooldownTime(tower.getCooldownObject().getCooldownTime() * cooldownMultiplier);
        tower.setValue(tower.getValue() * cooldownUpgradeCostMultiplier);
    }

    //getters for model

    public float getRadiusUpgradeCost(Tower tower) {
        return radiusUpgradeCostMultiplier * (tower.getValue()/2);
    }

    public float getDamageUpgradeCost(Tower tower) {
        return damageUpgradeCostMultiplier * (tower.getValue()/2);
    }

    public float getCooldownUpgradeCost(Tower tower) {
        return cooldownUpgradeCostMultiplier  * (tower.getValue()/2);
    }

    public float getRadiusMultiplier() {
        return radiusMultiplier;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public float getCooldownMultiplier() {
        return cooldownMultiplier;
    }
}
