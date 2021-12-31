package Game.Shop.Items;

import Game.Shop.Shop;

public class OrangeRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Orange Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a orange role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525064764067882L;
    }
}
