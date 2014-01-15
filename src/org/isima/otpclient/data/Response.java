/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.otpclient.data;

import java.util.Objects;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class Response {

    private transient Node fromNode;
    private transient Node toNode;
    private transient double duration;
    private transient double distance;

    public Response() {
    }

    public Response(Node fromNode, Node toNode, double duration, double distance) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.duration = duration;
        this.distance = distance;
    }

    public Response(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Response{" + "fromNode=" + fromNode.getPosition() + ", toNode=" + toNode.getPosition() + ", duration=" + duration + ", distance=" + distance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.fromNode);
        hash = 41 * hash + Objects.hashCode(this.toNode);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.duration) ^ (Double.doubleToLongBits(this.duration) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Response other = (Response) obj;
        if (!Objects.equals(this.fromNode, other.fromNode)) {
            return false;
        }
        if (!Objects.equals(this.toNode, other.toNode)) {
            return false;
        }
        if (Double.doubleToLongBits(this.duration) != Double.doubleToLongBits(other.duration)) {
            return false;
        }
        return true;
    }

}
