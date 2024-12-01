package jade.players;

public enum MoveStatus {
    ILLEGAL { @Override boolean isDone() { return false; }},
    DONE { @Override boolean isDone() { return true; }};
    abstract boolean isDone();
}
