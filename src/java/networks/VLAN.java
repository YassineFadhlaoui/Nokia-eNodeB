/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import Heat.HeatStack;
import servers.Server;

/**
 *
 * @author yassine
 */
public class VLAN {
    private Network Vlan;
    private Server Instance;
    private HeatStack stack;

    public HeatStack getStack() {
        return stack;
    }

    public void setStack(HeatStack stack) {
        this.stack = stack;
    }

    public VLAN(Network Vlan, Server Instance, HeatStack stack) {
        this.Vlan = Vlan;
        this.Instance = Instance;
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "VLAN{" + "Vlan=" + Vlan+ ", Instance=" + Instance + ", stack=" + stack + '}';
    }
    

    

    public Network getVlan() {
        return Vlan;
    }

    public Server getInstance() {
        return Instance;
    }


    public void setVlan(Network Vlan) {
        this.Vlan = Vlan;
    }

    public void setInstance(Server Instance) {
        this.Instance = Instance;
    }

}
