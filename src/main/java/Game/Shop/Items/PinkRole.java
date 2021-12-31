package Game.Shop.Items;

import Game.Shop.Shop;

public class PinkRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Pink Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a pink role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525103527833600L;
    }
}
