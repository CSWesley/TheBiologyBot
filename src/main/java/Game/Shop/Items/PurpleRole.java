package Game.Shop.Items;

import Game.Shop.Shop;

public class PurpleRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Purple Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a purple role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525152844476447L;
    }
}
