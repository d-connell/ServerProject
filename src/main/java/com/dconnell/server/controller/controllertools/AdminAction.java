package com.dconnell.server.controller.controllertools;

public class AdminAction {

    private final String displayText;
    private final String linkText;

    public AdminAction(String displayText, String linkText) {
        this.displayText = displayText;
        this.linkText = linkText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getLinkText() {
        return linkText;
    }

}