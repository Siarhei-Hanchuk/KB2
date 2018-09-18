package by.siarhei.kb2.app.server.actions;

import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.Game;

public class CityMenu extends Menu {
    private final int[] PRICE_WORKERS = {500, 500, 800, 800};
    private final int[] PRICE_MAGIC = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    private final int PRICE_NAVE = 500;
    private final int PRICE_WALLKICK = 3000;
    private final City city;

    CityMenu(Entity city, Game game) {
        super(game);
        this.city = (City) city;
    }

    @Override
    public boolean select(int i) {
//        switch (menuMode) {
//            case 0:
                switch (i) {
                    case 1:
                        if (game.naveExists()) {
                            game.destroyNave();
                        } else {
                            buyNave();
                        }
                        return false;
                    case 2:
//                        menuMode = 2;
                        return false;
                    case 3:
                        if (player.changeMoney(-PRICE_MAGIC[city.getMagic()]))
                            player.getMagic().upMagic(city.getMagic());
                    case 4:
                        if (!player.hasWallDestroyer()) {
                            player.changeMoney(-PRICE_WALLKICK);
                            player.setWallDestroyer();
                        }
                        return false;
                    case 5:
//                        menuMode = 1;
                        return false;

                }
                return false;
//            case 1:
//                if (city.getWorkers(i) > 0) {
//                    if (player.changeMoney(-PRICE_WORKERS[i])) {
//                        player.changeWorker(i, 1);
//                        city.changeWorkers(i, -1);
//                    }
//                }
//                return false;
//            case 2:
//                menuMode = 0;
//            default:
//                return false;
//        }
    }

    private void buyNave() {
        player.changeMoney(-PRICE_NAVE);
        int x;
        int y;
//        for (x = city.getX() - 1; x <= city.getX() + 1; x++) {
//            for (y = city.getY() - 1; y <= city.getY() + 1; y++) {
//                TODO:
//                if (city.getCountry().getMapPoint(x, y).getLand() == R.drawable.water) {
//                    game.createNave(city.getCountry(), x, y);
//                    return;
//                }
//                return;
//            }
//        }
        return;
    }
}
