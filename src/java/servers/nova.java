/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import helpers.AccessToken;
import helpers.HTTPOpenstackRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import networks.InstanceVirtualInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yassine
 */
public class nova {
    private String novaApiVersion;
    private String openstackServerIP;
    private String novaApiEndpointPort;
    private String httpMethod;
    private String preferredNokiaVMIP;
    private String nokiaVlanPattern;
    private String managementNetworkIpPattern;
    
    public nova(){
    
    Vector<String> Parameters = new Vector();
    Parameters.add("OPENSTACK_SERVER_IP");
    Parameters.add("NOVA_API_VERSION");
    Parameters.add("NOVA_API_PORT");
  
    Parameters.add("NOKIA_VLAN_IP_PATTERN");
    Parameters.add("MANAGEMENT_IP_PATTERN");
    
    
    Vector <String> params = helpers.Config.getProperties(Parameters);
    
    openstackServerIP=params.get(0);
    novaApiVersion=params.get(1);
    httpMethod="GET";
    novaApiEndpointPort=params.get(2);
    nokiaVlanPattern=params.get(3);
    managementNetworkIpPattern=params.get(4);
    
    
    }
    public List<Server> GetServers() {
        AccessToken token = new AccessToken();

        String Token = null;
        List<Server> list;
        list = new ArrayList<>();
        try {
            //GENERATE TOKEN
            Token = token.RequestAccessToken();
            //connect to endpoint
            String EndPointURL = "http://" + openstackServerIP + ":" + novaApiEndpointPort + "/" + novaApiVersion + "/" + "servers/detail";
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, httpMethod);
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject ServersObject = new JSONObject(sb.toString());
            //System.out.println(ServersObject);
            org.json.JSONArray servers = ServersObject.getJSONArray("servers");
            
            if(servers.length() > 0){
            for (int i = 0; i < servers.length(); i++) {
                
                String id = servers.getJSONObject(i).getString("id");
                String name = servers.getJSONObject(i).getString("name");
                String status = servers.getJSONObject(i).getString("status");
                //String key_name="nothin here";
                String key_name= servers.getJSONObject(i).getString("key_name");
                JSONObject addresses = new JSONObject(servers.getJSONObject(i).get("addresses").toString());
                
                Set set = addresses.keySet();
                Map<String,InstanceVirtualInterface> IPMap = new HashMap<String, InstanceVirtualInterface>();
                
                for (Object s : set) {
                    try{
                    org.json.JSONArray addr = addresses.getJSONArray(s.toString());
                    System.out.println(addr);
                    if(addr.length() > 0){
                        for(int item=0; item < addr.length(); item++){
                           
                           String  address =addr.getJSONObject(item).getString("addr");
                           String  mac =addr.getJSONObject(item).getString("OS-EXT-IPS-MAC:mac_addr");
                           //System.out.println(address);
                           InstanceVirtualInterface ivi = new InstanceVirtualInterface(address, mac);
                           IPMap.put(address,ivi);
                        }
                    }
                    }catch(Exception e){
                        //not able to retrieve ip addresses
                        }
                    }
                
                
                String VlanID="";

                InstanceVirtualInterface managementInterface = new InstanceVirtualInterface("","");
                InstanceVirtualInterface vlanInterface = new InstanceVirtualInterface("","");
                //loop through hash map
                Iterator mapiterator = IPMap.entrySet().iterator();
                while(mapiterator.hasNext()){
                Map.Entry entry = (Map.Entry)mapiterator.next();
                String ip=entry.getKey().toString();
  
                if(ip.contains(managementNetworkIpPattern)) {
                managementInterface.setIpAddress(ip);
                managementInterface.setMacAddress(IPMap.get(ip).getMacAddress());
                }
                if(ip.contains(nokiaVlanPattern)) {
                    
                    vlanInterface.setIpAddress(ip);
                    vlanInterface.setMacAddress(IPMap.get(ip).getMacAddress());
                            };
                //EquipementType+"VLAN-"+SegmentationID+"Private-Network";
                
                }
                String Parts [] = name.split("_");
                //for(String s : Parts) System.out.println(s);
                if (Parts.length > 1) VlanID=Parts[2];
                
                System.out.println(VlanID);
                Server server = new Server(id, name, status, key_name, VlanID,managementInterface,vlanInterface);
                System.out.println(server.toString());
                list.add(server);
            }
            }
        } catch (ParseException | IOException | ClassNotFoundException | JSONException ex) {
            //System.out.println("Could not request Access Token from : " + openstackServerIP);
            System.out.println(ex.toString());
        }
        return list;
    }
}
