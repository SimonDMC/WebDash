package com.simondmc.webdash.server;

import com.simondmc.webdash.config.RoutesConfig;

import java.util.ArrayList;
import java.util.List;

public class RouteHandler {

    private static final List<Route> routes = new ArrayList<>();

    public static void addRoute(Route route) {
        routes.add(route);
        RoutesConfig.saveRoutes();
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
        RoutesConfig.saveRoutes();
    }

    public static void loadRoutes() {
        routes.clear();
        routes.addAll(RoutesConfig.getRoutes());
    }
}
