package com.example.mobilesystems;

public class PageStackHandler {

    private int addedPagesCount = 0;
    private int removedPagesCount = 0;
    private int stackDepth = 0;

    public void pageAdded() {
        addedPagesCount++;
        stackDepth++;
    }

    public void pageRemoved() {
        if (stackDepth > 0) {
            removedPagesCount++;
            stackDepth--;
        }
    }

    public int getStackPage() {
        return stackDepth;
    }

    public int getAddedPagesCount() {
        return addedPagesCount;
    }

    public int getRemovedPagesCount() {
        return removedPagesCount;
    }
}
