package by.siarhei.kb2.app.server.models.battle;

public interface Interactor {
    void action(int x, int y);
    boolean finished();
}
