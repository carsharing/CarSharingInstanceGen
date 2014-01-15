/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.XML;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Hicham
 */
public class XMLNode {

    private String id;
    private String version;
    private String timeStamp;
    private String uid;
    private String user;
    private String changeset;
    private String lat;
    private String lon;
    private List<XMLNodeTags> tags;

    public XMLNode() {
    }

    public XMLNode(String id, String version, String timeStamp, String uid, String user, String changeset, String lat, String lon, List<XMLNodeTags> tags) {
        this.id = id;
        this.version = version;
        this.timeStamp = timeStamp;
        this.uid = uid;
        this.user = user;
        this.changeset = changeset;
        this.lat = lat;
        this.lon = lon;
        this.tags = tags;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setChangeset(String changeset) {
        this.changeset = changeset;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setTags(List<XMLNodeTags> tags) {
        this.tags = tags;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    @XmlAttribute(name="timestamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    @XmlAttribute
    public String getUid() {
        return uid;
    }

    @XmlAttribute
    public String getUser() {
        return user;
    }

    @XmlAttribute
    public String getChangeset() {
        return changeset;
    }

    @XmlAttribute
    public String getLat() {
        return lat;
    }

    @XmlAttribute
    public String getLon() {
        return lon;
    }

    @XmlElement(name = "tag")
    public List<XMLNodeTags> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.version);
        hash = 23 * hash + Objects.hashCode(this.timeStamp);
        hash = 23 * hash + Objects.hashCode(this.uid);
        hash = 23 * hash + Objects.hashCode(this.user);
        hash = 23 * hash + Objects.hashCode(this.changeset);
        hash = 23 * hash + Objects.hashCode(this.lat);
        hash = 23 * hash + Objects.hashCode(this.lon);
        hash = 23 * hash + Objects.hashCode(this.tags);
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
        final XMLNode other = (XMLNode) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.timeStamp, other.timeStamp)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.changeset, other.changeset)) {
            return false;
        }
        if (!Objects.equals(this.lat, other.lat)) {
            return false;
        }
        if (!Objects.equals(this.lon, other.lon)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

}
