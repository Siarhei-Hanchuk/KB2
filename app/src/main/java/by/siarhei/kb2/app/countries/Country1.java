package by.siarhei.kb2.app.countries;

import by.siarhei.kb2.app.R;

class Country1 extends Country {

    public Country1(boolean hard) {
        super();
        this.id = 0;
        baseGenerator.river(30);
        baseGenerator.river(20);
        baseGenerator.river(40);

        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        entityGenerator.armies(10, 1, 0);
        entityGenerator.mapNext();
        entityGenerator.captains(30, 400);
        createMetro();

        if (hard) {
            baseGenerator.landscape(0.33, R.drawable.forest);
            baseGenerator.landscape(0.33, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.14, R.drawable.forest);
            baseGenerator.landscape(0.05, R.drawable.stone);
        }
    }
}
