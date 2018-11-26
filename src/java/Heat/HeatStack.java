/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heat;


/**
 *
 * @author yassine
 */
public class HeatStack {
    private String Name;
    private String VLAN;
    private String ID;
    private String IPaddress;

    @Override
    public String toString() {
        return "HeatStack{" + "Name=" + Name + ", VLAN=" + VLAN + ", ID=" + ID + ", IPaddress=" + IPaddress + '}';
    }

    /**
     *
     * @param Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @param VLAN
     */
    public void setVLAN(String VLAN) {
        this.VLAN = VLAN;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @param IPaddress
     */
    public void setIPaddress(String IPaddress) {
        this.IPaddress = IPaddress;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @return
     */
    public String getVLAN() {
        return VLAN;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @return
     */
    public String getIPaddress() {
        return IPaddress;
    }

    /**
     *
     * @param Name
     * @param VLAN
     * @param ID
     * @param IPaddress
     */
    public HeatStack(String Name, String VLAN, String ID, String IPaddress) {
        this.Name = Name;
        this.VLAN = VLAN;
        this.ID = ID;
        this.IPaddress = IPaddress;
    }

    
}
