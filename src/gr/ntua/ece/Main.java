package gr.ntua.ece;


import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.*;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Input input = Input.getInstance();
        PrologSystem prolog = PrologSystem.getInstance();

        SimpleWeightedGraph<Node, DefaultWeightedEdge> graph = input.getGraph();
        int i = 0;
        int pointer = 0;
        Client currentClient = input.getClient();
        Double min = Double.MAX_VALUE;

        BufferedWriter bw = null;
        try {
            FileOutputStream outfile = new FileOutputStream("out.kml");
            bw = new BufferedWriter(new OutputStreamWriter(outfile));

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "\t<kml xmlns=\"http://earth.google.com/kml/2.1\">\n" +
                    "\t\t<Document> \n" +
                    "\t\t\t<name>\n" +
                    "\t\t\t\tTaxiRoutes\n" +
                    "\t\t\t</name> \n" +
                    "\t\t\t<Style id=\"green\"> \n" +
                    "\t\t\t\t<LineStyle> \n" +
                    "\t\t\t\t\t<color>\n" +
                    "\t\t\t\t\t\tff009900\n" +
                    "\t\t\t\t\t</color> \n" +
                    "\t\t\t\t\t<width>\n" +
                    "\t\t\t\t\t\t4\n" +
                    "\t\t\t\t\t</width> \n" +
                    "\t\t\t\t</LineStyle> \n" +
                    "\t\t\t</Style> \n" +
                    "\t\t\t<Style id=\"red\"> \n" +
                    "\t\t\t\t<LineStyle> \n" +
                    "\t\t\t\t\t<color>\n" +
                    "\t\t\t\t\t\tff0000ff\n" +
                    "\t\t\t\t\t</color> \n" +
                    "\t\t\t\t\t<width>\n" +
                    "\t\t\t\t\t\t4\n" +
                    "\t\t\t\t\t</width> \n" +
                    "\t\t\t\t</LineStyle> \n" +
                    "\t\t\t</Style> ");



            ArrayList<Taxi> validTaxis = new ArrayList<>();
            List<Integer> pointers = new ArrayList<>();

            for (Taxi taxi : input.getTaxis()) {
                i++;
                if (!(prolog.isDriverQualified(taxi.getId()))) {
                    continue;
                }
                Astar c = new Astar();

                List<Node> list = c.find(graph, taxi.getClosestNode(), currentClient.getClosestNode(), 15);

                System.out.println("Taxi No" + i + " Lat: " + taxi.getX() + " Long: " +
                        taxi.getY() + " Cost: " + c.getDistance());

                taxi.setCounter(i);
                taxi.setMin(c.getDistance());
                validTaxis.add(taxi);

                if (c.getDistance() == null) {
                    System.out.println("null Taxi" + i);
                } else if (c.getDistance() < min) {
                    pointer = i;
                    min = c.getDistance();
                }

                bw.write("<Placemark> \n" +
                        "\t\t\t\t<name>\n" +
                        "\t\t\t\t\tTaxi" +
                        String.valueOf(i) +
                        "\n" +
                        "\t\t\t\t</name> \n" +
                        "\t\t\t\t<styleUrl>\n" +
                        "\t\t\t\t\t#red\n" +
                        "\t\t\t\t</styleUrl> \n" +
                        "\t\t\t\t<LineString> \n" +
                        "\t\t\t\t\t<altitudeMode>\n" +
                        "\t\t\t\t\t\trelative\n" +
                        "\t\t\t\t\t</altitudeMode> \n" +
                        "\t\t\t\t\t<coordinates> ");

                for (Node p : list) {
                    bw.write(p.getX() + "," + p.getY());
                    bw.newLine();
                }

                bw.write("</coordinates> \n" +
                        "</LineString> \n" +
                        "</Placemark>");

            }

            System.out.println("Min cost: " + min);

            Collections.sort(validTaxis);
            System.out.println("Available taxis sorted by rating:");
            for (Taxi t : validTaxis) {
                System.out.println("Taxi No" + t.getCounter() + ", rating: " + t.getRating());
            }

            System.out.println("Please select taxi by its number: ");
            int choice = scanner.nextInt();

            Taxi chosenTaxi = null;
            for (Taxi t : validTaxis) {
                if (t.getCounter() == choice) {
                    chosenTaxi = t;
                }
            }

            if (chosenTaxi != null) {
                Astar c = new Astar();
                List<Node> list = c.find(graph, chosenTaxi.getClosestNode(), currentClient.getClosestNodeToDest(), 15);
                System.out.println("Min distance to destination = " + c.getDistance());
            } else {
                System.out.println("Invalid taxi choice.");
            }


            List<Taxi> allMinTaxis = new ArrayList<>();
            for (Taxi t : validTaxis) {
                if (t.getMin() == min) {
                    pointers.add(t.getCounter());
                }
            }

            Iterator<Taxi> myIterator = input.getTaxis().iterator();

            for (int deiktis : pointers) {
                for (int k = 0; k < deiktis - 1; ++k) {
                    myIterator.next();
                }
                allMinTaxis.add(myIterator.next());
                myIterator = input.getTaxis().iterator();
            }


            for (Taxi minimum : allMinTaxis) {
                Astar b = new Astar();

                List<Node> set = b.find(graph, minimum.getClosestNode(), currentClient.getClosestNode(), 10);
                bw.write("<Placemark> \n" +
                        "\t\t\t\t<name>\n" +
                        "\t\t\t\t\tTaxiMin\n" +
                        "\t\t\t\t</name> \n" +
                        "\t\t\t\t<styleUrl>\n" +
                        "\t\t\t\t\t#green\n" +
                        "\t\t\t\t</styleUrl> \n" +
                        "\t\t\t\t<LineString> \n" +
                        "\t\t\t\t\t<altitudeMode>\n" +
                        "\t\t\t\t\t\trelative\n" +
                        "\t\t\t\t\t</altitudeMode> \n" +
                        "\t\t\t\t\t<coordinates> ");

                for (Node p : set) {
                    bw.write(p.getX() + "," + p.getY());
                    bw.newLine();
                }

                bw.write("\t\t\t\t</coordinates> \n" +
                        "\t\t\t</LineString> \n" +
                        "\t\t</Placemark>\n");
            }
            bw.write("\t</Document> \n" +
                    "</kml>\n");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
