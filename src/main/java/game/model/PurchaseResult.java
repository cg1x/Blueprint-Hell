package game.model;

public record PurchaseResult(boolean successful, boolean alreadyPurchased, int coins) {
}
