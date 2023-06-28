package de.pixl.pixlbot.interactions.classes;

import de.pixl.pixlbot.interactions.interfaces.ModalExecutor;

public class Modal {

    private ModalExecutor executor;

    public void setExecutor(ModalExecutor executor) {
        this.executor = executor;
    }
    public ModalExecutor getExecutor() {
        return executor;
    }
}
