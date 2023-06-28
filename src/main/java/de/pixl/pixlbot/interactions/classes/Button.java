package de.pixl.pixlbot.interactions.classes;

import de.pixl.pixlbot.interactions.interfaces.ButtonExecutor;

public class Button {

    private ButtonExecutor executor;

    public void setExecutor(ButtonExecutor executor) {
        this.executor = executor;
    }
    public ButtonExecutor getExecutor() {
        return executor;
    }
}
