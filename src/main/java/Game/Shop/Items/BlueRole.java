package Game.Shop.Items;

import Game.Shop.Shop;

public class BlueRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Blue Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a blue role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926524908396236811L;
    }
}
