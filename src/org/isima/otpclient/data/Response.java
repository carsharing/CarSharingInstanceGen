/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.otpclient.data;

import java.util.Objects;

/**
 *
 * @author Hicham
 */
public class Response {

    private transient String fromLat;
    private transient String fromLon;
    private transient String toLat;
    private transient String toLon;
    private transient double duration;
    private transient double distance;

    public Response() {
    }

    public Response(String fromLat, String fromLon, String toLat, String toLon, double duration, double distance) {
        this.fromLat = fromLat;
        this.fromLon = fromLon;
        this.toLat = toLat;
        this.toLon = toLon;
        this.duration = duration;
        this.distance = distance;
    }

    public String getFromLat() {
        return fromLat;
    }

    public void setFromLat(String fromLat) {
        this.fromLat = fromLat;
    }

    public String getFromLon() {
        return fromLon;
    }

    public void setFromLon(String fromLon) {
        this.fromLon = fromLon;
    }

    public String getToLat() {
        return toLat;
    }

    public void setToLat(String toLat) {
        this.toLat = toLat;
    }

    public String getToLon() {
        return toLon;
    }

    public void setToLon(String toLon) {
        this.toLon = toLon;
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
        return "Response{" + "fromLat=" + fromLat + ", fromLon=" + fromLon + ", toLat=" + toLat + ", toLon=" + toLon + ", duration=" + duration + ", distance=" + distance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.fromLat);
        hash = 83 * hash + Objects.hashCode(this.fromLon);
        hash = 83 * hash + Objects.hashCode(this.toLat);
        hash = 83 * hash + Objects.hashCode(this.toLon);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.duration) ^ (Double.doubleToLongBits(this.duration) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
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
        if (!Objects.equals(this.fromLat, other.fromLat)) {
            return false;
        }
        if (!Objects.equals(this.fromLon, other.fromLon)) {
            return false;
        }
        if (!Objects.equals(this.toLat, other.toLat)) {
            return false;
        }
        if (!Objects.equals(this.toLon, other.toLon)) {
            return false;
        }
        if (Double.doubleToLongBits(this.duration) != Double.doubleToLongBits(other.duration)) {
            return false;
        }
        if (Double.doubleToLongBits(this.distance) != Double.doubleToLongBits(other.distance)) {
            return false;
        }
        return true;
    }

}
