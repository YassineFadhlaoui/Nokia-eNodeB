/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import networks.InstanceVirtualInterface;

/**
 *
 * @author yassine
 */
public class Server {
    private String id;
    private String name;
    private String status;
    private String KeyName;
    private String VlanID;
    private InstanceVirtualInterface managementInterrface;
    private InstanceVirtualInterface vlanInterrface;

    public Server(String id, String name, String status, String KeyName, String VlanID, InstanceVirtualInterface managementInterrface, InstanceVirtualInterface vlanInterrface) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.KeyName = KeyName;
        this.VlanID = VlanID;
        this.managementInterrface = managementInterrface;
        this.vlanInterrface = vlanInterrface;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyName() {
        return KeyName;
    }

    public void setKeyName(String KeyName) {
        this.KeyName = KeyName;
    }

    public String getVlanID() {
        return VlanID;
    }

    public void setVlanID(String VlanID) {
        this.VlanID = VlanID;
    }

    public InstanceVirtualInterface getManagementInterrface() {
        return managementInterrface;
    }

    public void setManagementInterrface(InstanceVirtualInterface managementInterrface) {
        this.managementInterrface = managementInterrface;
    }

    public InstanceVirtualInterface getVlanInterrface() {
        return vlanInterrface;
    }

    public void setVlanInterrface(InstanceVirtualInterface vlanInterrface) {
        this.vlanInterrface = vlanInterrface;
    }

    @Override
    public String toString() {
        return "Server{" + "id=" + id + ", name=" + name + ", status=" + status + ", KeyName=" + KeyName + ", VlanID=" + VlanID + ", managementInterrface=" + managementInterrface + ", vlanInterrface=" + vlanInterrface + '}';
    }

  


  
}
