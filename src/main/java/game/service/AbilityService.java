package game.service;

import game.model.*;

import java.util.ArrayList;
import java.util.List;

public class AbilityService {
    private List<Ability> activeAbilities;
    private GameState gameState;

    public AbilityService(GameState gameState) {
        activeAbilities = new ArrayList<>();
        this.gameState = gameState;
    }

    public void updateAbilities() {
        //Use backwards iteration to prevent list modification problems
        for (int i  = activeAbilities.size() - 1; i >= 0; i--) {
            Ability ability = activeAbilities.get(i);

            if (ability.getLifetime() == 0) {
                gameState.deactivateAbility(ability.getAbilityType());
                activeAbilities.remove(ability);
                continue;
            }

            ability.decrementLifeTime();
        }
    }

    public PurchaseResult purchaseAbility(AbilityType abilityType) {
        GameStats gameStats = gameState.getGameStats();

        if (gameState.isActive(abilityType)) {
            return new PurchaseResult(false, true, gameStats.getCoins());
        }

        if (abilityType.getPrice() > gameStats.getCoins()) {
            return new PurchaseResult(false, false, gameStats.getCoins());
        }

        addAbility(abilityType);
        gameStats.decrementCoins(abilityType.getPrice());

        return new PurchaseResult(true, false, gameStats.getCoins());
    }

    public void addAbility(AbilityType abilityType) {
        Ability ability = new Ability(abilityType, abilityType.getLifetime());
        activeAbilities.add(ability);
        gameState.activateAbility(abilityType);
    }
}
