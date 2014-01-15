/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements;

import java.util.Objects;

/**
 *
 * @author Hicham
 */
public class Node {

    private transient Double longitude;
    private transient Double latitude;
    private transient Integer capacity;
    private transient NodeMetaData metadata;

    public Node() {
        this.metadata = new NodeMetaData();
    }

    public Node(Double longitude, Double latitude, Integer capacity) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.capacity = capacity;
    }

    public Node(Double longitude, Double latitude, Integer capacity, NodeMetaData metadata) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.capacity = capacity;
        this.metadata = metadata;
    }

    public boolean isInfoComplete() {
        return (this.capacity != null) && (this.longitude != null) && (this.latitude != null);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public NodeMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(NodeMetaData metadata) {
        this.metadata = metadata;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.longitude);
        hash = 97 * hash + Objects.hashCode(this.latitude);
        hash = 97 * hash + Objects.hashCode(this.capacity);
        hash = 97 * hash + Objects.hashCode(this.metadata);
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
        final Node other = (Node) obj;
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.capacity, other.capacity)) {
            return false;
        }
        if (!Objects.equals(this.metadata, other.metadata)) {
            return false;
        }
        return true;
    }

}
