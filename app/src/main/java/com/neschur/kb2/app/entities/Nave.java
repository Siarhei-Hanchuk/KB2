package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Nave extends Entity implements Action{
    Country country;

	public Nave(Country country, int x, int y) {
		super(country, x, y);
        this.country = country;
	}

	@Override
	public int getID() {
		return R.drawable.nave;
	}

	public void action() {

	}

	public void move(int x,int y){
        country.getMap(x,y).setEntity(this);
        country.getMap(this.x,this.y).setEntity(null);
		this.x=x;
		this.y=y;
	}

    public void move(int x,int y, Country country){
        this.country.getMap(this.x,this.y).setEntity(null);
        this.country = country;
        move(x,y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
