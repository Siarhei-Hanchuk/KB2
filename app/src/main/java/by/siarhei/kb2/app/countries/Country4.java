package by.siarhei.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country4 extends Country {

    public Country4(boolean hard) {
        super();
        this.id = 3;
        baseGenerator.river(40);
        baseGenerator.river(30);

        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        entityGenerator.armies(5, 4);
        entityGenerator.mapNext();
        createMetro();

        if (hard) {
            baseGenerator.landscape(0.7, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.5, R.drawable.stone);
        }
    }
}
