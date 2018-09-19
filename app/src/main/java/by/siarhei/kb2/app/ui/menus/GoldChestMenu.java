package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class GoldChestMenu extends Menu {
    private final GoldenChest chest;
    private MapPoint mapPoint;

    GoldChestMenu(MapPoint mapPoint, Game game, I18n i18n) {
        super(game, i18n);
        this.chest = (GoldenChest) mapPoint.getEntity();
        this.mapPoint = mapPoint;
    }

    public String getTitle() {
        return i18n.translate("entity_goldChest_foundIt");
    }

    @Override
    public String getItemDescription(int i) {
        if (i == 0)
            return i18n.translate("entity_goldChest_variant1", chest.getGold());
        else
            return i18n.translate("entity_goldChest_variant2", chest.getAuthority());
    }

    @Override
    public int getItemImageId(int i) {
        if (i == 0)
            return R.drawable.status_money_0;
        else
            return R.drawable.status_army;
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 0:
                player.changeMoney(+chest.getGold());
                mapPoint.setEntity(null);
                break;
            case 1:
                player.changeAuthority(+chest.getAuthority());
                mapPoint.setEntity(null);
                break;
            default:
                return true;

        }
        return true;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
