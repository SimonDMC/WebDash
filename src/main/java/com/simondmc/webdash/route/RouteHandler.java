package com.simondmc.webdash.route;

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

    public static Route getRoute(int index) {
        for (Route route : routes) {
            if (route.getIndex() == index) {
                return route;
            }
        }
        return null;
    }

    // returns true if successful, false if route doesn't exist
    public static boolean removeRoute(String id) {

        // shift all routes after to be one less
        Route route = getRoute(id);
        if (route == null) return false;

        int index = route.getIndex();

        routes.remove(route);

        for (Route routeToShift : getRoutes()) {
            int indexToShift = routeToShift.getIndex();
            if (indexToShift > index) {
                routeToShift.setIndex(indexToShift - 1);
            }
        }

        RoutesConfig.saveRoutes();

        return true;
    }

    public static void loadRoutes() {
        routes.clear();
        routes.addAll(RoutesConfig.getRoutes());
    }

    public static String getJSON() {
        // compile JSON from routes
        StringBuilder json = new StringBuilder("{\"buttons\": [");
        for (Route route : RouteHandler.getRoutes()) {
            json.append(route.toJson()).append(",");
        }
        // remove trailing comma
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("]}");
        return json.toString();
    }
}
