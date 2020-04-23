package com.project.springblogapp.model;

import java.time.Instant;

public class Comment {

    private int id;
    private String commentedBy;
    private String content;
    private Instant commentedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Instant commentedOn) {
        this.commentedOn = commentedOn;
    }
}
