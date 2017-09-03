package by.siarhei.kb2.app.countries;

import by.siarhei.kb2.app.R;

class Country3 extends Country {

    public Country3(boolean hard) {
        super();
        this.id = 2;
        baseGenerator.river(40);
        baseGenerator.river(30);

        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        entityGenerator.armies(5, 3);
        entityGenerator.mapNext();
        createMetro();

        if (hard) {
            baseGenerator.landscape(0.7, R.drawable.forest);
        } else {
            baseGenerator.landscape(0.5, R.drawable.forest);
        }
    }
}
