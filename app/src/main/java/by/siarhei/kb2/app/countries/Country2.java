package by.siarhei.kb2.app.countries;

import by.siarhei.kb2.app.R;

public class Country2 extends Country {

    public Country2(boolean hard) {
        super();
        this.id = 1;
        baseGenerator.river(40);
        baseGenerator.river(50);
        baseGenerator.river(30);
        baseGenerator.river(50);
        baseGenerator.river(50);

        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        entityGenerator.armies(5, 2);
        entityGenerator.mapNext();
        entityGenerator.captains(150, 1200);
        createMetro();

        if (hard) {
            baseGenerator.landscape(0.125, R.drawable.water);
            baseGenerator.landscape(0.125, R.drawable.forest);
            baseGenerator.landscape(0.05, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.14, R.drawable.water);
            baseGenerator.landscape(0.08, R.drawable.forest);
            baseGenerator.landscape(0.03, R.drawable.stone);
        }
    }
}
