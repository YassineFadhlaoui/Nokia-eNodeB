/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import java.util.List;
import java.util.Vector;
import networks.Network;
import networks.neutron;
import networks.subnet;

/**
 *
 * @author yassine
 */
public class ManagementNetworkInfo {
    String InternalMangementNetwork;
  public ManagementNetworkInfo(){
      Vector<String> Parameters = new Vector();
        Parameters.add("INTERNAL_MANAGEMENT_NETWORK");
        
        Vector <String> params = helpers.Config.getProperties(Parameters);
    
    InternalMangementNetwork=params.get(0);
      
    }
    neutron Neutron = new neutron();
  
    public Network getManagementExternalNetwork() {

        List<Network> list = Neutron.GetNetworks();
        for (Network network : list) {
            if (network.getType().equalsIgnoreCase("flat")) {
                return network;
            }
        }
        return null;
    }

    public subnet getExternalMangementSubnet() {

        List<subnet> subnets = Neutron.GetSubnetss();
        for (subnet subnet : subnets) {
            if (subnet.getParentID().equals(getManagementExternalNetwork().getId())) {
                return subnet;
            }
        }
        return null;
    }

    public subnet getInternalMangementSubnet() {

        List<subnet> subnets = Neutron.GetSubnetss();
        for (subnet subnet : subnets) {
            if (subnet.getCidr().contains(InternalMangementNetwork)) {
                return subnet;
            }
        }
        return null;
    }

    public Network getPrivateMangementNetwork() {
        subnet sub = getInternalMangementSubnet();
        String ParentID = sub.getParentID();
        List<Network> list = Neutron.GetNetworks();
        for (Network network : list) {
            if (network.getId().equals(ParentID)) {
                return network;
            }
        }
        return null;
    }
}
