/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.utilities;

import java.math.BigInteger;
import org.isima.carsharing.elements.Node;
import org.isima.otpclient.data.NodeMatrix;
import org.isima.otpclient.data.Response;

/**
 *
 * @author Hicham
 */
public class GraphFactory {

    public Simulation createCompleteGraph(NodeMatrix nodeMatrix) {
        Simulation simulation;
        Simulation.Locations locations;
        Simulation.Distances distances;

        GraphBuilder graphBuilder = new GraphBuilder();
        simulation = graphBuilder.createSimulation();

        locations = graphBuilder.createSimulationLocations();
        distances = graphBuilder.createSimulationDistances();

        Simulation.Locations.Location location;
        Simulation.Locations.Location.Position position;
        for (Node node : nodeMatrix.getNodes()) {
            location = graphBuilder.createSimulationLocationsLocation();
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationCapacity(BigInteger.valueOf(node.getCapacity().longValue())));
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationDriverAvailable(BigInteger.valueOf(node.getDriverAvailable().longValue())));
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationId(node.getMetadata().getId()));
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationParkedCar(BigInteger.valueOf(node.getParkedCar().longValue())));
            position = graphBuilder.createSimulationLocationsLocationPosition();
            //TODO : Fix X/Y Lat/Lon converstion
            position.getXOrY().add(graphBuilder.createSimulationLocationsLocationPositionX(BigInteger.valueOf(node.getLongitude().longValue())));
            position.getXOrY().add(graphBuilder.createSimulationLocationsLocationPositionY(BigInteger.valueOf(node.getLatitude().longValue())));
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationPosition(position));
            location.getParkedCarOrCapacityOrDriverAvailable().add(graphBuilder.createSimulationLocationsLocationType("node type"));

            locations.getLocation().add(location);
        }

        Response response;
        Integer distanceID = 0;
        Simulation.Distances.Distance distance;
        for (Node fromNode : nodeMatrix.getNodes()) {
            for (Node toNode : nodeMatrix.getNodes()) {
                distanceID++;
                response = nodeMatrix.getValue(fromNode, toNode);
                distance = graphBuilder.createSimulationDistancesDistance();

                //TODO : convert in xsd from int to double
                distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceDist(BigInteger.valueOf(response.getDistance().longValue())));
                distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceFrom(response.getFromNode().getMetadata().getId()));
                distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceTo(response.getToNode().getMetadata().getId()));
                distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceId(BigInteger.valueOf(distanceID)));

                distances.getDistance().add(distance);
            }
        }
        simulation.setLocations(locations);
        simulation.setDistances(distances);
        simulation.setRequests(graphBuilder.createSimulationRequests());
        return simulation;

    }
}
