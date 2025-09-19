package game.model;

import static game.controller.Constants.UPS;

public enum AbilityType {
    O_ATAR(10 * UPS, 3),
    O_AIRYAMAN(5 * UPS, 4),
    O_ANAHITA(1, 5)
    ;

    private final int lifetime;
    private final int price;

    AbilityType(int lifetime, int price) {
        this.lifetime = lifetime;
        this.price = price;
    }

    public int getLifetime() {
        return lifetime;
    }

    public int getPrice() {
        return price;
    }
}
