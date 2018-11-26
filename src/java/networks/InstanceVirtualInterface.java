/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

/**
 *
 * @author occ
 */
public class InstanceVirtualInterface {
   private String  IpAddress;
   private String macAddress;

    public InstanceVirtualInterface() {
    }

    public InstanceVirtualInterface(String IpAddress, String macAddress) {
        this.IpAddress = IpAddress;
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setIpAddress(String IpAddress) {
        this.IpAddress = IpAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "InstanceVirtualInterface{" + "IpAddress=" + IpAddress + ", macAddress=" + macAddress + '}';
    }
   
}
