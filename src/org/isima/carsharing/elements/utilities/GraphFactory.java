/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.utilities;

import java.math.BigInteger;
import java.util.Set;
import org.isima.carsharing.elements.Node;
import org.isima.otpclient.data.NodeMatrix;
import org.isima.otpclient.data.Response;

/**
 *
 * @author Hicham
 */
public class GraphFactory {

    public Simulation createCompleteGraph(NodeMatrix nodeMatrix) {
        return this.createGraph(nodeMatrix, true);
    }

    public Simulation createGraph(NodeMatrix nodeMatrix, boolean complete) {
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
                response = nodeMatrix.getValue(fromNode, toNode);
                if (complete == true) {
                    distanceID++;
                    distance = graphBuilder.createSimulationDistancesDistance();

                    //TODO : convert in xsd from int to double
                    distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceDist(BigInteger.valueOf(response.getDistance().longValue())));
                    distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceFrom(response.getFromNode().getMetadata().getId()));
                    distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceTo(response.getToNode().getMetadata().getId()));
                    distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceId(BigInteger.valueOf(distanceID)));

                    distances.getDistance().add(distance);
                } else {
                    if (response.isAlive()) {
                        distanceID++;
                        distance = graphBuilder.createSimulationDistancesDistance();

                        //TODO : convert in xsd from int to double
                        distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceDist(BigInteger.valueOf(response.getDistance().longValue())));
                        distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceFrom(response.getFromNode().getMetadata().getId()));
                        distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceTo(response.getToNode().getMetadata().getId()));
                        distance.getDistOrFromOrTo().add(graphBuilder.createSimulationDistancesDistanceId(BigInteger.valueOf(distanceID)));

                        distances.getDistance().add(distance);

                    }
                }
            }
        }
        simulation.setLocations(locations);
        simulation.setDistances(distances);
        simulation.setRequests(graphBuilder.createSimulationRequests());
        return simulation;

    }

    public Simulation createIncompleteGraph(NodeMatrix nodeMatrix,Float distanceMaring) {
        double firstStepDistance, secondStepDistance, directPathDistance;
        double firstStepTime, secondStepTime, directPathTime;
        Response directPath;
        //At first all paths are alive
        Set<Response> alivePaths = nodeMatrix.getAllValues();
        int markedPathsNumber = 0, killedPathsNumber = 0, alivePathsNumber = alivePaths.size();
        //Get all nodes
        for (Node originNode : nodeMatrix.getNodes()) {
            //Get first step time and duration
            for (Response firstStep : nodeMatrix.getLine(originNode)) {
                //First step distination must be different from the origin and must be alive
                if (!firstStep.getToNode().equals(originNode) && firstStep.isAlive()) {
                    firstStepDistance = firstStep.getDistance();
                    firstStepTime = firstStep.getDuration();
                    //Get second step time and duration
                    for (Response secondStep : nodeMatrix.getLine(firstStep.getToNode())) {
                        //Second step must start at the firstStep end, its destination must be different from the origin node and must be alive
                        if (secondStep.getFromNode().equals(firstStep.getToNode()) && !secondStep.getToNode().equals(firstStep.getToNode()) && !secondStep.getToNode().equals(originNode) && secondStep.isAlive()) {
                            secondStepDistance = secondStep.getDistance();
                            secondStepTime = secondStep.getDuration();

                            //Get direct path from origin to secondStep end
                            directPath = nodeMatrix.getValue(originNode, secondStep.getToNode());
                            directPathDistance = directPath.getDistance();
                            directPathTime = directPath.getDuration();
                            //To test the direct path it must be alive and not marqued
                            if (directPath.isMarqued() == false && directPath.isAlive() == true) {
                                //Test & marque and eventually kill a path
                                if (directPathDistance + distanceMaring >= firstStepDistance + secondStepDistance) {
                                    directPath.setAlive(false);
                                    directPath.setMarqued(true);
                                    alivePaths.remove(directPath);
                                    alivePathsNumber = alivePaths.size();
                                    killedPathsNumber++;
                                    markedPathsNumber++;
                                } 
                                /* Not working woth time for now
                                else if (directPathTime >= firstStepTime + secondStepTime) {
                                    directPath.setAlive(false);
                                    directPath.setMarqued(true);
                                    alivePaths.remove(directPath);
                                    alivePathsNumber = alivePaths.size();
                                    killedPathsNumber++;
                                    markedPathsNumber++;
                                } */
                                else {
                                    directPath.setMarqued(true);
                                    markedPathsNumber++;
                                }
                            }
                        } else if (secondStep.getToNode().equals(firstStep)) {
                            secondStep.setMarqued(true);
                            markedPathsNumber++;
                        }
                    }
                } else if (firstStep.getToNode().equals(originNode) && firstStep.isMarqued()==false) {
//                    firstStep.setMarqued(true);
//                    markedPathsNumber++;
                    
                    
                    firstStep.setAlive(false);
                    firstStep.setMarqued(true);
                    alivePaths.remove(firstStep);
                    alivePathsNumber = alivePaths.size();
                    killedPathsNumber++;
                    markedPathsNumber++;
                    
                }
            }

            if ((markedPathsNumber - killedPathsNumber) == alivePathsNumber) {
                //All alive paths has been tested so exit the loop
                break;
            }
        }
        System.out.println("marked: " + markedPathsNumber + " killed: " + killedPathsNumber + " alive: " + alivePathsNumber);
        return this.createGraph(nodeMatrix, false);
    }

}
