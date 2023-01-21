package org.example.Event.model;

public class Event {

    public int previousEventX, previousEventY;
    public boolean canTouchEvent;

    public Event() {
        this.canTouchEvent = true;
    }
}
