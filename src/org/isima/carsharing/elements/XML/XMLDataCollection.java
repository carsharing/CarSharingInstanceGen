/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.XML;

import java.util.Collection;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hicham
 */
@XmlRootElement(name = "osm")
public class XMLDataCollection {

    private static class Bounds {

        @XmlAttribute
        public String minlon;
        @XmlAttribute
        public String minlat;
        @XmlAttribute
        public String maxlon;
        @XmlAttribute
        public String maxlat;
        @XmlAttribute
        public String origin;

        public Bounds() {
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 13 * hash + Objects.hashCode(this.minlon);
            hash = 13 * hash + Objects.hashCode(this.minlat);
            hash = 13 * hash + Objects.hashCode(this.maxlon);
            hash = 13 * hash + Objects.hashCode(this.maxlat);
            hash = 13 * hash + Objects.hashCode(this.origin);
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
            final Bounds other = (Bounds) obj;
            if (!Objects.equals(this.minlon, other.minlon)) {
                return false;
            }
            if (!Objects.equals(this.minlat, other.minlat)) {
                return false;
            }
            if (!Objects.equals(this.maxlon, other.maxlon)) {
                return false;
            }
            if (!Objects.equals(this.maxlat, other.maxlat)) {
                return false;
            }
            if (!Objects.equals(this.origin, other.origin)) {
                return false;
            }
            return true;
        }

    }

    private String version;
    private String generator;
    @XmlElement(name="bounds")
    private Bounds bounds;
    private Collection<XMLNode> nodes;

    public XMLDataCollection() {
    }

    public XMLDataCollection(String version, String generator, Bounds bounds, Collection<XMLNode> nodes) {
        this.version = version;
        this.generator = generator;
        this.bounds = bounds;
        this.nodes = nodes;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setNodes(Collection<XMLNode> nodes) {
        this.nodes = nodes;
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    @XmlAttribute
    public String getGenerator() {
        return generator;
    }

    
    public Bounds getBounds() {
        return bounds;
    }

    @XmlElement(name = "node")
    public Collection<XMLNode> getNodes() {
        return nodes;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.version);
        hash = 29 * hash + Objects.hashCode(this.generator);
        hash = 29 * hash + Objects.hashCode(this.bounds);
        hash = 29 * hash + Objects.hashCode(this.nodes);
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
        final XMLDataCollection other = (XMLDataCollection) obj;
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.generator, other.generator)) {
            return false;
        }
        if (!Objects.equals(this.bounds, other.bounds)) {
            return false;
        }
        if (!Objects.equals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }

}
