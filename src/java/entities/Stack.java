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
@Table(name = "Stack")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stack.findAll", query = "SELECT s FROM Stack s"),
    @NamedQuery(name = "Stack.findByStackId", query = "SELECT s FROM Stack s WHERE s.stackId = :stackId"),
    @NamedQuery(name = "Stack.findByStackName", query = "SELECT s FROM Stack s WHERE s.stackName = :stackName"),
    @NamedQuery(name = "Stack.findByInstanceIp", query = "SELECT s FROM Stack s WHERE s.instanceIp = :instanceIp"),
    @NamedQuery(name = "Stack.findByInactivity", query = "SELECT s FROM Stack s WHERE s.inactivity = :inactivity")})
public class Stack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "stack_id")
    private String stackId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "stack_name")
    private String stackName;
    @Size(max = 20)
    @Column(name = "instance_ip")
    private String instanceIp;
    @Column(name = "inactivity")
    private Integer inactivity;

    public Stack() {
    }

    public Stack(String stackId) {
        this.stackId = stackId;
    }

    public Stack(String stackId, String stackName) {
        this.stackId = stackId;
        this.stackName = stackName;
    }

    public String getStackId() {
        return stackId;
    }

    public void setStackId(String stackId) {
        this.stackId = stackId;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getInstanceIp() {
        return instanceIp;
    }

    public void setInstanceIp(String instanceIp) {
        this.instanceIp = instanceIp;
    }

    public Integer getInactivity() {
        return inactivity;
    }

    public void setInactivity(Integer inactivity) {
        this.inactivity = inactivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stackId != null ? stackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stack)) {
            return false;
        }
        Stack other = (Stack) object;
        if ((this.stackId == null && other.stackId != null) || (this.stackId != null && !this.stackId.equals(other.stackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stack[ stackId=" + stackId + " ]";
    }
    
}
