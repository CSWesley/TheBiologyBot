package Game.Shop.Items;

import Game.Shop.Shop;

public class GreenRole implements Shop {

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "Green Role";
    }

    @Override
    public String getDescription() {
        return "When purchased, you will be given a green role and placed separately from the other users.";
    }

    @Override
    public long getId() {
        return 926525019192979506L;
    }
}
