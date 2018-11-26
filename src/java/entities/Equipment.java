/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yassine
 */
@Entity
@Table(name = "Equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findByEquipmentID", query = "SELECT e FROM Equipment e WHERE e.equipmentID = :equipmentID"),
    @NamedQuery(name = "Equipment.findBySourceCONumber", query = "SELECT e FROM Equipment e WHERE e.sourceCONumber = :sourceCONumber"),
    @NamedQuery(name = "Equipment.findBySourcedevicePort", query = "SELECT e FROM Equipment e WHERE e.sourcedevicePort = :sourcedevicePort"),
    @NamedQuery(name = "Equipment.findByVlanid", query = "SELECT e FROM Equipment e WHERE e.vlanid = :vlanid"),
    @NamedQuery(name = "Equipment.findByDestinationCONumber", query = "SELECT e FROM Equipment e WHERE e.destinationCONumber = :destinationCONumber"),
    @NamedQuery(name = "Equipment.findByDestinationPort", query = "SELECT e FROM Equipment e WHERE e.destinationPort = :destinationPort"),
    @NamedQuery(name = "Equipment.findByStatus", query = "SELECT e FROM Equipment e WHERE e.status = :status"),
    @NamedQuery(name = "Equipment.findByComment", query = "SELECT e FROM Equipment e WHERE e.comment = :comment")})
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EquipmentID")
    private Integer equipmentID;
    @Size(max = 250)
    @Column(name = "Source_CO_Number")
    private String sourceCONumber;
    @Size(max = 100)
    @Column(name = "Source_device_Port")
    private String sourcedevicePort;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "VLANID")
    private String vlanid;
    @Size(max = 250)
    @Column(name = "Destination_CO_Number")
    private String destinationCONumber;
    @Size(max = 250)
    @Column(name = "Destination_Port")
    private String destinationPort;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 500)
    @Column(name = "comment")
    private String comment;

    public Equipment() {
    }

    public Equipment(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Equipment(Integer equipmentID, String vlanid) {
        this.equipmentID = equipmentID;
        this.vlanid = vlanid;
    }

    public Integer getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getSourceCONumber() {
        return sourceCONumber;
    }

    public void setSourceCONumber(String sourceCONumber) {
        this.sourceCONumber = sourceCONumber;
    }

    public String getSourcedevicePort() {
        return sourcedevicePort;
    }

    public void setSourcedevicePort(String sourcedevicePort) {
        this.sourcedevicePort = sourcedevicePort;
    }

    public String getVlanid() {
        return vlanid;
    }

    public void setVlanid(String vlanid) {
        this.vlanid = vlanid;
    }

    public String getDestinationCONumber() {
        return destinationCONumber;
    }

    public void setDestinationCONumber(String destinationCONumber) {
        this.destinationCONumber = destinationCONumber;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipmentID != null ? equipmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.equipmentID == null && other.equipmentID != null) || (this.equipmentID != null && !this.equipmentID.equals(other.equipmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipment{" + "equipmentID=" + equipmentID + ", sourceCONumber=" + sourceCONumber + ", sourcedevicePort=" + sourcedevicePort + ", vlanid=" + vlanid + ", destinationCONumber=" + destinationCONumber + ", destinationPort=" + destinationPort + ", status=" + status + ", comment=" + comment + '}';
    }

   
    
}
