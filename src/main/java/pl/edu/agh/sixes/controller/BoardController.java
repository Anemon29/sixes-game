package pl.edu.agh.sixes.controller;

import pl.edu.agh.sixes.command.CommandRegistry;

public class BoardController {

    private AppController appController;

    private CommandRegistry commandRegistry;


    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

}
