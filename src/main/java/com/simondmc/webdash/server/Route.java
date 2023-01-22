package com.simondmc.webdash.server;

public class Route {

    private final String name;
    private final String command;
    private String id;

    public Route(String name, String command) {
        this.name = name;
        this.command = command;
        // generate id from name based on the following criteria:
        // 1. all spaces are replaced with underscores
        // 2. all non-alphanumeric characters are removed
        // 3. all letters are converted to lowercase
        // 4. if id already exists (RouteHandler.get(id) != null), add an underscore to the end
        this.id = name.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        while (RouteHandler.getRoute(id) != null) {
            this.id += "_";
        }
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public String getId() {
        return id;
    }

    public String toJson() {
        return "{\"name\": \"" + name + "\", \"command\": \"/" + command + "\", \"id\": \"" + id + "\", \"color\": \"#5781af\", \"index\": 0}";
    }
}
