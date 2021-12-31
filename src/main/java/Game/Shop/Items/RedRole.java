package Game.Shop.Items;

import Game.Shop.Shop;

public class RedRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Red Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a red role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525271094485022L;
    }
}
