package Game.Shop.Items;

import Game.Shop.Shop;

public class YellowRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Yellow Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a yellow role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525337922306049L;
    }
}
