/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import helpers.AccessToken;
import helpers.HTTPOpenstackRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yassine
 */
public class neutron {

    private static final String GET = "GET";
    private String openstackServerIP;
    private String neutronApiVersion;
    private String neutronApiEndpointPort;
    private final String neutronEndpoint;
    private final String neutronSubnetEndpoint;
    
    
    
    public neutron(){
    Vector<String> Parameters = new Vector();
    Parameters.add("OPENSTACK_SERVER_IP");
    Parameters.add("NEUTRON_API_VERSION");
    Parameters.add("NEUTRON_API_PORT");
    Parameters.add("NEUTRON_ENDPOINT");
    Parameters.add("NEUTRON_SUBNET_ENDPOINT");
  
    Vector <String> params = helpers.Config.getProperties(Parameters);
    
    openstackServerIP=params.get(0);
    neutronApiVersion=params.get(1);
    neutronApiEndpointPort=params.get(2);
    neutronEndpoint=params.get(3);
    neutronSubnetEndpoint=params.get(4);
    
    }
    public List<Network> GetNetworks() {
        AccessToken token = new AccessToken();

        String Token = null;
        List<Network> list;
        list = new ArrayList<>();
        try {
            //GENERATE TOKEN
            Token = token.RequestAccessToken();
            //connect to endpoint
            String EndPointURL = "http://" + openstackServerIP + ":" + neutronApiEndpointPort + "/" + neutronApiVersion + "/" + neutronEndpoint;
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, GET);
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject ServersObject = new JSONObject(sb.toString());
            //System.out.println(ServersObject);
            org.json.JSONArray networks = ServersObject.getJSONArray("networks");
            if (networks.length() > 0) {
                for (int i = 0; i < networks.length(); i++) {
                    String name = networks.getJSONObject(i).getString("name");
                    String status = networks.getJSONObject(i).getString("status");
                    String NetworkType = String.valueOf(networks.getJSONObject(i).get("provider:network_type"));
                    String SegmentationID = String.valueOf(networks.getJSONObject(i).get("provider:segmentation_id"));
                    String id = networks.getJSONObject(i).getString("id");
                    Network network = new Network(name, SegmentationID, NetworkType, status,id);
                    //System.out.println(network.toString());
                    list.add(network);
                }
            }
        } catch (ParseException | IOException | ClassNotFoundException | JSONException ex) {
            System.out.println(ex.toString());
        }
        return list;
    }
    
        public List<subnet> GetSubnetss() {
        AccessToken token = new AccessToken();

        String Token = null;
        List<subnet> list;
        list = new ArrayList<>();
        try {
            //GENERATE TOKEN
            Token = token.RequestAccessToken();
            //connect to endpoint
            String EndPointURL = "http://" + openstackServerIP + ":" + neutronApiEndpointPort + "/" + neutronApiVersion + "/" + neutronSubnetEndpoint;
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, GET);
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject ServersObject = new JSONObject(sb.toString());
            //System.out.println(ServersObject);
            org.json.JSONArray subnets = ServersObject.getJSONArray("subnets");
            //System.out.println(subnets.toString());
            if (subnets.length() > 0) {
                for (int i = 0; i < subnets.length(); i++) {
                    String name = subnets.getJSONObject(i).getString("name");
                    String cidr = subnets.getJSONObject(i).getString("cidr");
                    String created_at = subnets.getJSONObject(i).getString("created_at");
                    String gateway_ip= subnets.getJSONObject(i).getString("gateway_ip");
                    String network_id = subnets.getJSONObject(i).getString("network_id");
                    org.json.JSONArray AllocationPools = subnets.getJSONObject(i).getJSONArray("allocation_pools");
                    String start=null;
                    String end=null;
                    for(int j = 0; j < AllocationPools.length(); j++) {
                        start = AllocationPools.getJSONObject(j).getString("start");
                        end = AllocationPools.getJSONObject(j).getString("end");
                    }
                    Map<String,String> pool= new LinkedHashMap<>();
                    pool.put("Start", start);
                    pool.put("End", end);
                    
                    subnet sub = new subnet(name, network_id, cidr, pool, created_at, gateway_ip);
                    //System.out.println(sub.toString());
                    list.add(sub);
                }
            }
        } catch (ParseException | IOException | ClassNotFoundException | JSONException ex) {
            System.out.println(ex.toString());
        }
        return list;
    }
        
    
}
