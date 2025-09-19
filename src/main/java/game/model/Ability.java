package game.model;

public class Ability {
    private AbilityType abilityType;
    private int lifetime;

    public Ability(AbilityType abilityType, int lifetime) {
        this.abilityType = abilityType;
        this.lifetime = lifetime;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public void decrementLifeTime() {
        lifetime --;
    }
}
