/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

/**
 *
 * @author yassine
 */
public class Server {
    private String id;
    private String name;
    private String status;
    private String KeyName;
    private String FloatingIP;
    private String VlanID;
    private String VlanIP;

    @Override
    public String toString() {
        return "Server{" + "id=" + id + ", name=" + name + ", status=" + status + ", KeyName=" + KeyName + ", FloatingIP=" + FloatingIP + ", VlanID=" + VlanID + ", VlanIP=" + VlanIP + '}';
    }

    public String getVlanIP() {
        return VlanIP;
    }

    public void setVlanIP(String VlanIP) {
        this.VlanIP = VlanIP;
    }

 


    public String getKeyName() {
        return KeyName;
    }

    public String getFloatingIP() {
        return FloatingIP;
    }

    public void setKeyName(String KeyName) {
        this.KeyName = KeyName;
    }

    public void setFloatingIP(String FloatingIP) {
        this.FloatingIP = FloatingIP;
    }
    
    //overload 

    public Server(String id, String name, String status, String KeyName, String FloatingIP, String VlanID, String VlanIP) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.KeyName = KeyName;
        this.FloatingIP = FloatingIP;
        this.VlanID = VlanID;
        this.VlanIP = VlanIP;
    }


    public void setVlanID(String VlanID) {
        this.VlanID = VlanID;
    }

    public String getVlanID() {
        return VlanID;
    }

    

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
