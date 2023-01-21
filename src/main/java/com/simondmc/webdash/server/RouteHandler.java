package com.simondmc.webdash.server;

import java.util.ArrayList;
import java.util.List;

public class RouteHandler {

    private static final List<Route> routes = new ArrayList<>();

    public static void addRoute(Route route) {
        routes.add(route);
    }

    public static List<Route> getRoutes() {
        return routes;
    }

    public static Route getRoute(String id) {
        for (Route route : routes) {
            if (route.getId().equals(id)) {
                return route;
            }
        }
        return null;
    }

    public static void removeRoute(String id) {
        routes.remove(getRoute(id));
    }
}
