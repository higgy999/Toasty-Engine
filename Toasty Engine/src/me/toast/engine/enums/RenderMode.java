package me.toast.engine.enums;

public enum RenderMode {
    IMMEDIATE(0),
    DISPLAY_LIST(1),
    VERTEX_ARRAYS(2),
    VBO(3)
    ;

    private int mode;
    RenderMode(int mode) {
        this.mode = mode;
    }
    public int getMode() { return mode; }
}
