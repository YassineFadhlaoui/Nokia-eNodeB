/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

/**
 *
 * @author yassine
 */
public class ManagementNetwork {
    private String ExternalManagementNet;
    private String ExternalManagementSub;
    private String InternalManagementNet;
    private String InternalManagementSub;

    public ManagementNetwork(String ExternalManagementNet, String ExternalManagementSub, String InternalManagementNet, String InternalManagementSub) {
        this.ExternalManagementNet = ExternalManagementNet;
        this.ExternalManagementSub = ExternalManagementSub;
        this.InternalManagementNet = InternalManagementNet;
        this.InternalManagementSub = InternalManagementSub;
    }

    public String getExternalManagementNet() {
        return ExternalManagementNet;
    }

    public String getExternalManagementSub() {
        return ExternalManagementSub;
    }

    public String getInternalManagementNet() {
        return InternalManagementNet;
    }

    public String getInternalManagementSub() {
        return InternalManagementSub;
    }

    public void setExternalManagementNet(String ExternalManagementNet) {
        this.ExternalManagementNet = ExternalManagementNet;
    }

    public void setExternalManagementSub(String ExternalManagementSub) {
        this.ExternalManagementSub = ExternalManagementSub;
    }

    public void setInternalManagementNet(String InternalManagementNet) {
        this.InternalManagementNet = InternalManagementNet;
    }

    public void setInternalManagementSub(String InternalManagementSub) {
        this.InternalManagementSub = InternalManagementSub;
    }
    
    
}
