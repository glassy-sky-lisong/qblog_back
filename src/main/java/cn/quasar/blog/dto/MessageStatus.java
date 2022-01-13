package cn.quasar.blog.dto;

public enum MessageStatus {

    SUCCESS(200), FAIL(500);

    private int status;

    private MessageStatus(int statusNumber) {
        this.status = statusNumber;
    }

    public int getStatus() {
        return this.status;
    }
}
