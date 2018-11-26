/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.util.Map;

/**
 *
 * @author yassine
 */
public class subnet {
    private String name;
    private String ParentID;
    private String cidr;
    private Map<String,String> AllocationPools;
    private String CreationTime;
    private String GatewayIP;

    @Override
    public String toString() {
        return "subnet{" + "name=" + name + ", ParentID=" + ParentID + ", cidr=" + cidr + ", AllocationPools=" + AllocationPools + ", CreationTime=" + CreationTime + ", GatewayIP=" + GatewayIP + '}';
    }

    public subnet(String name, String ParentID, String cidr, Map<String, String> AllocationPools, String CreationTime, String GatewayIP) {
        this.name = name;
        this.ParentID = ParentID;
        this.cidr = cidr;
        this.AllocationPools = AllocationPools;
        this.CreationTime = CreationTime;
        this.GatewayIP = GatewayIP;
    }


    public String getName() {
        return name;
    }

    public String getParentID() {
        return ParentID;
    }

    public String getCidr() {
        return cidr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public void setAllocationPools(Map<String, String> AllocationPools) {
        this.AllocationPools = AllocationPools;
    }

    public void setCreationTime(String CreationTime) {
        this.CreationTime = CreationTime;
    }

    public void setGatewayIP(String GatewayIP) {
        this.GatewayIP = GatewayIP;
    }

    public Map<String, String> getAllocationPools() {
        return AllocationPools;
    }

    public String getCreationTime() {
        return CreationTime;
    }

    public String getGatewayIP() {
        return GatewayIP;
    }
    
    
    
}
