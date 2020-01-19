package me.toast.engine.enums;

public enum RenderState {
    FULL(0),
    HOLLOW(1),
    TEXTURED(2)
    ;

    private final int state;
    RenderState(int state) {
        this.state = state;
    }
    public int getState() { return state; }
}
