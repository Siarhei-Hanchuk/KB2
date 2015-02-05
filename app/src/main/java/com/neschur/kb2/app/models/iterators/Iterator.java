package com.neschur.kb2.app.models.iterators;

public interface Iterator<Type> {
    public void init(int id);

    public Type next();

    public boolean isDone();
}
