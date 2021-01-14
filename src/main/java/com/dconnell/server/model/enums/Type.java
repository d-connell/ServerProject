package com.dconnell.server.model.enums;

import java.util.EnumSet;

public enum Type {

    BAGS("bags"),
    BLANKETS("blankets"),
    HATS("hats"),
    QUILTS("quilts");

    private final String label;

    Type(String label) {
        this.label = label;
    }

    public static Type findService(String input) {
        return EnumSet.allOf(Type.class)
                .stream()
                .filter(operationType -> operationType.label.equals(input))
                .reduce(null, (acc, element) -> element);
    }

    public String getLabel() {
        return label;
    }

}