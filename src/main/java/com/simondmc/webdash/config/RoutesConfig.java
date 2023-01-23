package com.simondmc.webdash.config;

import com.simondmc.webdash.server.Route;
import com.simondmc.webdash.server.RouteHandler;

import java.util.ArrayList;
import java.util.List;

public class RoutesConfig {

        private static String CONFIG_NAME = "routes.yml";

        public static void init() {
            Configs.add(CONFIG_NAME);
            RouteHandler.loadRoutes();
        }

        public static List<Route> getRoutes() {
            List<Route> routes = new ArrayList<>();
            for (String key : Configs.get(CONFIG_NAME).getConfig().getKeys(false)) {
                String path = key + ".";
                String name = Configs.get(CONFIG_NAME).getConfig().getString(path + "name");
                String command = Configs.get(CONFIG_NAME).getConfig().getString(path + "command");
                String color = Configs.get(CONFIG_NAME).getConfig().getString(path + "color");
                int index = Configs.get(CONFIG_NAME).getConfig().getInt(path + "index");
                Route route = new Route(name, command, color, index, key);
                routes.add(route);
            }
            return routes;
        }

        public static void saveRoutes() {
            for (Route route : RouteHandler.getRoutes()) {
                String path = route.getId() + ".";
                Configs.get(CONFIG_NAME).getConfig().set(path + "name", route.getName());
                Configs.get(CONFIG_NAME).getConfig().set(path + "command", route.getCommand());
                Configs.get(CONFIG_NAME).getConfig().set(path + "color", route.getColor());
                Configs.get(CONFIG_NAME).getConfig().set(path + "index", route.getIndex());
            }
            Configs.get(CONFIG_NAME).save();
        }
}
