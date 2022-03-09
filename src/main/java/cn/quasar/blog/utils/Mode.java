package cn.quasar.blog.utils;

public enum Mode {

    NO(0), OR(1), NOT(2), AND(3);

    private int code;

    Mode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
