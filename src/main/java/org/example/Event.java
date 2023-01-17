package org.example;

public class Event {

    public int previousEventX, previousEventY;
    public boolean canTouchEvent;

    public Event() {
        this.canTouchEvent = true;
    }
}
