/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import helpers.Config;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

/**
 *
 * @author yassine
 */
public class TemplateEngine {

    private String error = "";
    public static final String BASH_SCRIPT = scriptInjector.SCRIPT;

    public Map<String, Object> parameters() {
        String ExternalManagementNet = null;
        String ExternalManagementSub = null;
        String InternalManagementNet = null;
        String InternalManagementSub = null;

        ManagementNetworkInfo mninfo = new ManagementNetworkInfo();
        //get management network information
        try {
            ExternalManagementNet = mninfo.getManagementExternalNetwork().getName();
            ExternalManagementSub = mninfo.getExternalMangementSubnet().getName();
            InternalManagementNet = mninfo.getPrivateMangementNetwork().getName();
            InternalManagementSub = mninfo.getInternalMangementSubnet().getName();

            if (ExternalManagementNet == null || ExternalManagementSub == null || InternalManagementNet == null || InternalManagementSub == null) {
                throw new ManagementNetworkException("Cannot retrieve all Mangement network information");
            }
        } catch (ManagementNetworkException ex) {
            System.out.println(ex);
            error = "Cannot retrieve all Mangement network information. Please make sure that your management network is created and reachable";
        }

        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, String> external_management_network = new LinkedHashMap<>();
        external_management_network.put("type", "string");
        external_management_network.put("description", "external management network management");
        external_management_network.put("default", ExternalManagementNet);

        Map<String, String> external_mangement_subnet = new LinkedHashMap<>();
        external_mangement_subnet.put("type", "string");
        external_mangement_subnet.put("description", "The name of the subnet in the external management network");
        external_mangement_subnet.put("default", ExternalManagementSub);

        Map<String, String> private_management_network = new LinkedHashMap<>();
        private_management_network.put("type", "string");
        private_management_network.put("description", "the private management network linked to the external management network");
        private_management_network.put("default", InternalManagementNet);

        Map<String, String> private_management_subnet = new LinkedHashMap<>();
        private_management_subnet.put("type", "string");
        private_management_subnet.put("description", "the private subnet associated with the private management network");
        private_management_subnet.put("default", InternalManagementSub);

        RSAKeyGen RSAKeyPair = new RSAKeyGen();
        String KeyName = RSAKeyPair.generateKeyUUID();
        String PrivateKey = RSAKeyPair.GenerateStackKey(KeyName);
        String keyStorePath= new RSAKeyGen().CreateKeyStoreDir();
        //System.out.println(keyStorePath + KeyName + ".pem");
        try (PrintWriter writer = new PrintWriter(keyStorePath + KeyName + ".pem", "UTF-8")) {
            writer.write(PrivateKey);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
        Map<String, String> Key_Pair_Name = new LinkedHashMap<>();
        Key_Pair_Name.put("type", "string");
        Key_Pair_Name.put("description", "An automatically generated RSA key  using NOVA REST API");
        Key_Pair_Name.put("default", KeyName);

        map.put("external_management_network", external_management_network);
        map.put("external_mangement_subnet", external_mangement_subnet);
        map.put("private_management_network", private_management_network);
        map.put("private_management_subnet", private_management_subnet);
        map.put("Key_Pair_Name", Key_Pair_Name);

        return map;
    }

    public String getError() {
        return error;
    }

    public Map<String, Object> VLANProviderNet(String VLANName, String SegmentationID) {
        
        Vector<String> Parameters = new Vector();
                Parameters.add("VLAN_PROVIDER_NAME");
                Vector<String> params = Config.getProperties(Parameters);
                String VLANProvider = params.get(0);
        Map<String, Object> VLAN = new LinkedHashMap<>();
        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("admin_state_up", Boolean.TRUE);
        attributes.put("name", VLANName);
        attributes.put("network_type", "vlan");
        attributes.put("physical_network", VLANProvider);
        attributes.put("port_security_enabled", Boolean.TRUE);
        attributes.put("router_external", Boolean.TRUE);
        attributes.put("segmentation_id", Integer.parseInt(SegmentationID));
        attributes.put("shared", Boolean.TRUE);

        VLAN.put("type", "OS::Neutron::ProviderNet");
        VLAN.put("properties", attributes);
        System.out.println("VLAN provider ok!");
        return VLAN;
    }

    public Map<String, Object> Externalsubnet(String PrivateSubnetName, String ExternalNetworkName, String Equipment) {
        System.out.println("External subnet for "+Equipment);
        Map<String, Object> subnet = new LinkedHashMap<>();
        
        Vector<String> Parameters = new Vector();
        if(Equipment.equalsIgnoreCase("NOKIA")){
            
        Parameters.add("NOKIA_EXTERNAL_VLAN_SUBNET_START");
        Parameters.add("NOKIA_EXTERNAL_VLAN_SUBNET_END");
        Parameters.add("NOKIA_EXTERNAL_VLAN_NETWORK_ADDRESS");
        //System.out.println("here");
        }else if(Equipment.equalsIgnoreCase("Huawei")){
        Parameters.add("HW_EXTERNAL_VLAN_SUBNET_START");
        Parameters.add("HW_EXTERNAL_VLAN_SUBNET_END");
        Parameters.add("HW_EXTERNAL_VLAN_NETWORK_ADDRESS");
        }
        //Vector<String> Parameters = new Vector();
        Vector<String> params = Config.getProperties(Parameters);
        String poolStart= params.get(0);
        String poolEnd = params.get(1);
        String VlanNetworkAddress= params.get(2);
        
        System.out.println(poolStart+" "+poolEnd+" "+VlanNetworkAddress);
        List allocation_pools = new LinkedList();
        JSONObject allocation = new JSONObject();
        allocation.accumulate("start", poolStart);
        allocation.accumulate("end", poolEnd);
        allocation_pools.add(allocation);

        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", PrivateSubnetName);
        Map<String, String> network_idMap = new HashMap<>();
        network_idMap.put("get_resource", ExternalNetworkName);
        properties.put("network_id", network_idMap);
        properties.put("cidr", VlanNetworkAddress);
        properties.put("ip_version", Integer.parseInt("4"));
        properties.put("allocation_pools", allocation_pools);

        subnet.put("type", "OS::Neutron::Subnet");
        subnet.put("properties", properties);
        System.out.println("Subnet ok");
        return subnet;

    }

    public Map<String, Object> CentosInstanceBlock(String KeyPairName, String Internal, String External) {

        Map<String, Object> Instance = new LinkedHashMap<>();
        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, Object> keyName = new LinkedHashMap<>();
        keyName.put("get_param", "Key_Pair_Name");
        properties.put("key_name", keyName);
        
        Vector<String> Parameters = new Vector();
        Parameters.add("INSTANCE_FLAVOR");
        Parameters.add("GLANCE_IMAGE_NAME");
        
        Vector<String> params = Config.getProperties(Parameters);
        String flavor= params.get(0);
        String glanceImage= params.get(1);
        
        properties.put("flavor", flavor);
        properties.put("image", glanceImage);

        List ports = new ArrayList();
        Map<String, Object> port1 = new LinkedHashMap<>();
        Map<String, Object> port2 = new LinkedHashMap<>();
        Map<String, Object> resource1 = new LinkedHashMap<>();
        Map<String, Object> resource2 = new LinkedHashMap<>();
        resource1.put("get_resource", Internal);
        resource2.put("get_resource", External);
        port1.put("port", resource1);
        port2.put("port", resource2);
        ports.add(port1);
        ports.add(port2);

        properties.put("networks", ports);

        properties.put("user_data_format", "RAW");
        properties.put("user_data", BASH_SCRIPT.trim());

        Instance.put("type", "OS::Nova::Server");
        Instance.put("properties", properties);
        System.out.println("instance ok");
        return Instance;

    }

    public Map<String, Object> RouterTemplate(String Network, String RouterName) {

        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, Object> RouterBlock = new LinkedHashMap<>();
        Map<String, String> external_gatewayMap = new HashMap<>();
        external_gatewayMap.put("network", Network);
        properties.put("external_gateway_info", external_gatewayMap);
        RouterBlock.put("type", "OS::Neutron::Router");
        RouterBlock.put("properties", properties);

        return RouterBlock;
    }

    public Map<String, Object> InternalNetworkBlock(String InternalNetworkName) {

        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("name", InternalNetworkName);

        Map<String, Object> InternalNetworkTemplate = new LinkedHashMap<>();
        InternalNetworkTemplate.put("type", "OS::Neutron::Net");
        InternalNetworkTemplate.put("properties", properties);

        return InternalNetworkTemplate;
    }

    public Map<String, Object> InternalNetworkSubnetBlock(String SubnetName, String InternalNetworkName) {

        
        Vector<String> Parameters = new Vector();
        Parameters.add("INTERNAL_VLANS_SUBNET_START");
        Parameters.add("INTERNAL_VLANS_SUBNET_END");
        Parameters.add("INTERNAL_VLANS_NETWORK_ADDRESS");
        
        Vector<String> params = Config.getProperties(Parameters);
        String poolStart= params.get(0);
        String poolEnd= params.get(1);
        String internalNetworkAddress=params.get(2);
        
        
        Map<String, Object> SubnetTemplate = new LinkedHashMap<>();
        List pool = new LinkedList();
        JSONObject pools = new JSONObject();
        pools.put("start", poolStart);
        pools.put("end", poolEnd);
        pool.add(pools);
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", SubnetName);
        Map<String, String> network_idMap = new HashMap<>();
        network_idMap.put("get_resource", InternalNetworkName);
        properties.put("network_id", network_idMap);
        properties.put("cidr", internalNetworkAddress);
        properties.put("allocation_pools", pool);
        properties.put("ip_version", 4);

        SubnetTemplate.put("type", "OS::Neutron::Subnet");
        SubnetTemplate.put("properties", properties);

        return SubnetTemplate;

    }

    public Map<String, Object> RouterINterfaceBlock(String RouterInterfaceName, String RouterName, String InternalSubnetName) {

        Map<String, Object> RouterInterface = new LinkedHashMap<>();
        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, String> router_idMap = new LinkedHashMap<>();
        Map<String, String> SubnetMap = new LinkedHashMap<>();
        router_idMap.put("get_resource", RouterName);
        properties.put("router_id", router_idMap);
        SubnetMap.put("get_resource", InternalSubnetName);

        properties.put("subnet", SubnetMap);
        RouterInterface.put("type", "OS::Neutron::RouterInterface");
        RouterInterface.put("properties", properties);

        return RouterInterface;

    }

    public Map<String, Object> VlanFloatingIP(String VLANFloatingIP, String floating_network, String floating_subnet) {

        Map<String, Object> FloatingIPTemplate = new LinkedHashMap<>();
        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, String> floating_networkMap = new HashMap<>();
        Map<String, String> floating_subnetMap = new HashMap<>();
        floating_networkMap.put("get_resource", floating_network);
        floating_subnetMap.put("get_resource", floating_subnet);
        properties.put("floating_network", floating_networkMap);
        properties.put("floating_subnet", floating_subnetMap);
        properties.put("floating_ip_address","192.168.255.129");
        
        FloatingIPTemplate.put("type", "OS::Neutron::FloatingIP");
        FloatingIPTemplate.put("properties", properties);

        return FloatingIPTemplate;

    }

    public Map<String, Object> MangementFloatingIPBlock(String ManagementFloatingIP, String ExternalManagementNetwork, String ExternalSubnet) {

        Map<String, Object> ManagementFloatingIPTemplate = new LinkedHashMap<>();
        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, String> floating_networkMap = new HashMap<>();
        Map<String, String> floating_subnetMap = new HashMap<>();
        floating_networkMap.put("get_param", ExternalManagementNetwork);
        floating_subnetMap.put("get_param", ExternalSubnet);

        properties.put("floating_network", floating_networkMap);
        properties.put("floating_subnet", floating_subnetMap);

        ManagementFloatingIPTemplate.put("type", "OS::Neutron::FloatingIP");
        ManagementFloatingIPTemplate.put("properties", properties);

        return ManagementFloatingIPTemplate;
    }

    public Map<String, Object> PortBlockGenerator(String PortName, String Subnet, String Network, String Query) {

        Map<String, Object> PortTemplate = new LinkedHashMap<>();
        Map<String, Object> properties = new LinkedHashMap<>();

        List<Map> IP = new ArrayList();
        Map<String, Object> subnet_id = new HashMap<>();
        Map<String, String> param_subnet_id = new HashMap<>();
        param_subnet_id.put(Query, Subnet);
        subnet_id.put("subnet_id", param_subnet_id);
        IP.add(subnet_id);

        Map<String, String> network_id = new HashMap<>();
        network_id.put(Query, Network);
        properties.put("network_id", network_id);

        properties.put("fixed_ips", IP);

        PortTemplate.put("type", "OS::Neutron::Port");
        PortTemplate.put("properties", properties);

        return PortTemplate;

    }

    public Map<String, Object> VLANFloatingIPAssociation(String BlockName, String VLANFloatingIP, String InternalPortName) {

        Map<String, Object> VlanFloatingIPTemplate = new LinkedHashMap<>();

        Map<String, Object> properties = new LinkedHashMap<>();

        Map<String, String> floatingip_idMap = new HashMap<>();
        floatingip_idMap.put("get_resource", VLANFloatingIP);
        properties.put("floatingip_id", floatingip_idMap);
        Map<String, String> port_idMap = new HashMap<>();
        port_idMap.put("get_resource", InternalPortName);
        properties.put("port_id", port_idMap);

        VlanFloatingIPTemplate.put("type", "OS::Neutron::FloatingIPAssociation");
        VlanFloatingIPTemplate.put("properties", properties);

        return VlanFloatingIPTemplate;

    }

    public Map<String, Object> ManagementFloatingIPAssociation(String BlockName, String ManagementFloatingIP, String ExternalPortName, String dep) {

        Map<String, Object> ManagementFloatingIPTemplate = new LinkedHashMap<>();

        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, String> ManagementFloatingIPMap = new LinkedHashMap<>();
        ManagementFloatingIPMap.put("get_resource", ManagementFloatingIP);
        properties.put("floatingip_id", ManagementFloatingIPMap);
        Map<String, String> resourceMap = new HashMap<>();
        resourceMap.put("get_resource", ExternalPortName);

        properties.put("port_id", resourceMap);

        ManagementFloatingIPTemplate.put("type", "OS::Neutron::FloatingIPAssociation");
        ManagementFloatingIPTemplate.put("properties", properties);
        List dependencies = new ArrayList();
        dependencies.add(dep);
        ManagementFloatingIPTemplate.put("depends_on", dependencies);

        return ManagementFloatingIPTemplate;

    }

    public JSONObject GenerateTemplate(String EquipementType, String VLANName, String SegmentationID) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String VLAN = EquipementType + "-" + "VLAN-" + SegmentationID;
        String ExternalSubnetName = "Subnet-" + EquipementType + "vlan-" + SegmentationID;
        String RouterName = EquipementType + "-vlan" + SegmentationID + "router";
        String PrivateNetwork = EquipementType + "VLAN-" + SegmentationID + "-Private-Network";
        String PrivateSubnet = EquipementType + "VLAN-" + SegmentationID + "-Private-Subnet";
        String InternalRouterInterface = EquipementType + "VLAN-" + SegmentationID + "-Internaliface";
        String VLANFloatingIPName = EquipementType + "-VLAN-" + SegmentationID + "-FloatingIP";
        String ManagementFloatingIPName = "Mangement-FloatingIP";
        String ExternalManagementNetworkParam = "external_management_network";
        String ExternalManagementSubnetParam = "external_mangement_subnet";
        String InternalPortName = "Internal-Port";
        String ExternalPortName = "External-Port";
        String PrivateSubnetManagement = "private_management_subnet";
        String PrivateNetworkManagement = "private_management_network";
        String FloatingIPVlanAssociation = "Vlan-floating-ip-association";
        String FloatingIPManagementAssociation = "Management-floating-ip-association";
        String KeyPairName = "Generated-KeyPair";

        Map<String, Object> Parameters = parameters();

        Map<String, Object> ExternalVlan = VLANProviderNet(VLAN, SegmentationID);
        Map<String, Object> ExternalSubnet = Externalsubnet(ExternalSubnetName, VLAN,EquipementType);
        Map<String, Object> ExternalRouter = RouterTemplate(VLAN, RouterName);
        Map<String, Object> PrivateInternalNetwork = InternalNetworkBlock(PrivateNetwork);
        Map<String, Object> InternalSubnet = InternalNetworkSubnetBlock(PrivateSubnet, PrivateNetwork);
        Map<String, Object> RouterInterface = RouterINterfaceBlock(InternalRouterInterface, RouterName, PrivateSubnet);
        Map<String, Object> VLANFloatingIP = VlanFloatingIP(VLANFloatingIPName, VLAN, ExternalSubnetName);

        Map<String, Object> ManagementFloatingIP = MangementFloatingIPBlock(ManagementFloatingIPName, ExternalManagementNetworkParam, ExternalManagementSubnetParam);
        Map<String, Object> InternalPort = PortBlockGenerator(InternalPortName, PrivateSubnet, PrivateNetwork, "get_resource");
        Map<String, Object> ExternalPort = PortBlockGenerator(ExternalPortName, PrivateSubnetManagement, PrivateNetworkManagement, "get_param");

        Map<String, Object> Centos = CentosInstanceBlock(KeyPairName, InternalPortName, ExternalPortName);

        Map<String, Object> VlanFloatingIPAssociation = VLANFloatingIPAssociation(FloatingIPVlanAssociation, VLANFloatingIPName, InternalPortName);
        Map<String, Object> ManagementFloatingIPAssociation = ManagementFloatingIPAssociation(FloatingIPManagementAssociation, ManagementFloatingIPName, ExternalPortName, FloatingIPVlanAssociation);

        Map<String, Object> collection = new LinkedHashMap<>();

        collection.put(VLAN, ExternalVlan);
        collection.put(ExternalSubnetName, ExternalSubnet);
        collection.put(RouterName, ExternalRouter);
        collection.put(PrivateNetwork, PrivateInternalNetwork);
        collection.put(PrivateSubnet, InternalSubnet);
        collection.put(InternalRouterInterface, RouterInterface);
        collection.put(VLANFloatingIPName, VLANFloatingIP);
        collection.put(ManagementFloatingIPName, ManagementFloatingIP);
        collection.put(InternalPortName, InternalPort);
        collection.put(ExternalPortName, ExternalPort);
        collection.put("Centos", Centos);
        collection.put(FloatingIPVlanAssociation, VlanFloatingIPAssociation);
        collection.put(FloatingIPManagementAssociation, ManagementFloatingIPAssociation);

        Map<String, Object> HOTTemplate = new LinkedHashMap<>();
        HOTTemplate.put("heat_template_version", "2017-09-01");
        HOTTemplate.put("parameters", Parameters);
        HOTTemplate.put("resources", collection);

        //now lets add some extra attributes according to heat REST API documentation
        //https://docs.openstack.org/heat/latest/template_guide/openstack.html


        JSONObject jsonHttpRequestBody = new JSONObject(HOTTemplate);

        return jsonHttpRequestBody;
    }

}
