package by.siarhei.kb2.app.server.models.battle;

interface BattleParticipant {
    void action(int x, int y);
    boolean finished();
}
