/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haryoken
 */
@Entity
@Table(name = "tbl_room", catalog = "HotelDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRoom.findAll", query = "SELECT t FROM TblRoom t"),
    @NamedQuery(name = "TblRoom.findByRoomID", query = "SELECT t FROM TblRoom t WHERE t.roomID = :roomID"),
    @NamedQuery(name = "TblRoom.findByDescription", query = "SELECT t FROM TblRoom t WHERE t.description = :description"),
    @NamedQuery(name = "TblRoom.findByHourPrice", query = "SELECT t FROM TblRoom t WHERE t.hourPrice = :hourPrice"),
    @NamedQuery(name = "TblRoom.findByDayPrice", query = "SELECT t FROM TblRoom t WHERE t.dayPrice = :dayPrice")})
public class TblRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "roomID", nullable = false, length = 3)
    private String roomID;
    @Column(name = "description", length = 250)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hourPrice", precision = 53)
    private Double hourPrice;
    @Column(name = "dayPrice", precision = 53)
    private Double dayPrice;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne
    private String categoryID;

    public TblRoom() {
    }

    public TblRoom(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Double hourPrice) {
        this.hourPrice = hourPrice;
    }

    public Double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRoom)) {
            return false;
        }
        TblRoom other = (TblRoom) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.entity.TblRoom[ roomID=" + roomID + " ]";
    }
    
}
