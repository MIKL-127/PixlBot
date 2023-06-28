package de.pixl.pixlbot.interactions.classes;

import de.pixl.pixlbot.interactions.interfaces.ButtonExecutor;
import de.pixl.pixlbot.interactions.interfaces.SelectMenuExecutor;

public class SelectMenu {

    private SelectMenuExecutor executor;

    public void setExecutor(SelectMenuExecutor executor) {
        this.executor = executor;
    }
    public SelectMenuExecutor getExecutor() {
        return executor;
    }
}
