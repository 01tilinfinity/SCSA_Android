package com.scsa.goatsaeng;

public class TodoItem {
    private int id;
    private String content;
    private boolean isDone;

    public TodoItem(int id, String content, boolean isDone) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
