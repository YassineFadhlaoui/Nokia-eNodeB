/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

/**
 *
 * @author yassine
 */
public class Router {
    private String IPaddress;
    private String ExternalInterface;
    private String InternalInterface;

    public Router(String IPaddress, String ExternalInterface, String InternalInterface) {
        this.IPaddress = IPaddress;
        this.ExternalInterface = ExternalInterface;
        this.InternalInterface = InternalInterface;
    }

    public String getIPaddress() {
        return IPaddress;
    }

    public String getExternalInterface() {
        return ExternalInterface;
    }

    public String getInternalInterface() {
        return InternalInterface;
    }

    public void setIPaddress(String IPaddress) {
        this.IPaddress = IPaddress;
    }

    public void setExternalInterface(String ExternalInterface) {
        this.ExternalInterface = ExternalInterface;
    }

    public void setInternalInterface(String InternalInterface) {
        this.InternalInterface = InternalInterface;
    }
    
    
    
}
