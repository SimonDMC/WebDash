package com.simondmc.webdash.server;

import com.simondmc.webdash.util.StringUtil;

public class Route {

    private String name;
    // command is stored without a leading slash and added visually
    private String command;
    private String id;
    private String color;
    private int index;

    // new route
    public Route(String name, String command) {
        this(name, command, "#5781af", RouteHandler.getRoutes().size(), StringUtil.generateId(name));
    }

    public Route(String name, String command, String color) {
        this(name, command, color, RouteHandler.getRoutes().size(), StringUtil.generateId(name));
    }

    // load existing route
    public Route(String name, String command, String color, int index, String id) {
        this.name = name;
        this.command = command;
        this.color = color;
        this.index = index;
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toJson() {
        // make sure all quotes are escaped exactly once
        String command = StringUtil.escapeQuotes(StringUtil.unescapeQuotes(this.command));
        return "{\"name\":\"" + name + "\",\"command\":\"/" + command + "\",\"id\":\"" + id + "\",\"color\":\"" + color + "\",\"index\":" + index + "}";
    }
}
