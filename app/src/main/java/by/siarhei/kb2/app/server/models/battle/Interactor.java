package by.siarhei.kb2.app.server.models.battle;

public interface Interactor {
    void action(MapPointBattle mapPointBattle);
    boolean finished();
}
