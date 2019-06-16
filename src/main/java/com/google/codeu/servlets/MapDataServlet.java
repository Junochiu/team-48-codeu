package com.google.codeu.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

/**
 * Returns UFO data as a JSON array, e.g. [{"lat": 38.4404675, "lng": -122.7144313}]
 */
@WebServlet("/map-data")
public class MapDataServlet extends HttpServlet {

    private JsonArray SightingArray;

    @Override
    public void init() {
        SightingArray = new JsonArray();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/mapdata.csv"));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split(",");

            double lat = Double.parseDouble(cells[0]);
            double lng = Double.parseDouble(cells[1]);

            SightingArray.add(gson.toJsonTree(new Sighting(lat, lng)));
        }
        scanner.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getOutputStream().println(SightingArray.toString());
    }

    // This class could be its own file if we needed it outside this servlet
    private static class Sighting{
        double lat;
        double lng;

        private Sighting(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }
}