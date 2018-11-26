package Heat;

import helpers.AccessToken;
import helpers.HTTPOpenstackRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.json.JSONObject;

/**
 *
 * @author yassine
 */
public class stack {


    private  String openstackServer;
    private  String tenantID;
    private  String heatEndpointPort;
    private  String heatApiVersion;
    private  String internalManagementNetworkAddr;
    
    /**
     *
     */
    public stack(){
    Vector <String> Parameters = new Vector();
            Parameters.add("OPENSTACK_SERVER_IP");
            Parameters.add("TENANT_ID");
            Parameters.add("HEAT_ENDPOINT_PORT");
            Parameters.add("HEAT_API_VERSION");
            Parameters.add("INTERNAL_MANAGEMENT_NETWORK");
            
           Vector <String> params= helpers.Config.getProperties(Parameters);
           openstackServer=params.get(0);
           tenantID= params.get(1);
           heatEndpointPort= params.get(2);
           heatApiVersion = params.get(3);
           internalManagementNetworkAddr=params.get(4);
    
    }

    /**
     *
     * @return
     */
    public List<HeatStack> getStacks(){
       AccessToken token = new AccessToken();

        String Token = null;
        List<HeatStack> list;
        list = new ArrayList<>();
        try {
            //GENERATE TOKEN
            Token = token.RequestAccessToken();
            //connect to endpoint
            String EndPointURL = "http://" + openstackServer+ ":" + heatEndpointPort + "/" + heatApiVersion+ "/" +tenantID+"/"+ "stacks";
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, "GET");
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject ServersObject = new JSONObject(sb.toString());
            //System.out.println(ServersObject);
            org.json.JSONArray stacks = ServersObject.getJSONArray("stacks");
            //System.out.println(servers);
            if(stacks.length() > 0){
                for (int i = 0; i < stacks.length(); i++) {
                String id = stacks.getJSONObject(i).getString("id");
                String name = stacks.getJSONObject(i).getString("stack_name");
                String NetworkResources = "";
                String IP="";
                String VLAN="";
                try{
                    NetworkResources= getResources(name, Token, id);
                }catch(Exception e){//not vlan
                        }
                if(NetworkResources.length() > 0){
                     IP=NetworkResources.split(":")[0];
                    VLAN=NetworkResources.split(":")[1];
                }
               
                HeatStack Stack = new HeatStack(name, VLAN, id, IP);
                //System.out.println(Stack);
                list.add(Stack);
            }
            }
        return list;
   }catch(Exception e){
    System.out.println(e);
    return list;
}

   }
    
   private String getResources(String stackname,String Token, String stackid) throws IOException{

            String EndPointURL = "http://" + openstackServer + ":" + heatEndpointPort + "/" + heatApiVersion + "/" +tenantID+"/"+ "stacks"+"/"+stackname+"/"+stackid+"/"+"resources/Centos";
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, "GET");
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject ResourceObject = new JSONObject(sb.toString());
            //System.out.println(ServersObject);
            org.json.JSONObject addresses =  ResourceObject.getJSONObject("resource").getJSONObject("attributes").getJSONObject("addresses");
            
            Set set = addresses.keySet();
            String IP=null;
            String VLAN=null;
                for (Object s : set) {
                    try{
                    org.json.JSONArray addr = addresses.getJSONArray(s.toString());
                    
                    if(addr.length() > 0){
                        for(int item=0; item < addr.length(); item++){
                           String  address =addr.getJSONObject(item).getString("addr");
                           if(address.contains(internalManagementNetworkAddr)) IP=address;
                           if(s.toString().contains("VLAN")) VLAN=s.toString();
                        }
                    }
                    }catch(Exception e){
                        return "";
                        }
                    }
         return IP+":"+VLAN;
   }
   
    /**
     *
     * @param StackName
     * @param StackID
     * @return
     */
    public String deleteStacks(String StackName,String StackID){
       AccessToken token = new AccessToken();

        String Token = null;

        try {
            //GENERATE TOKEN
            Token = token.RequestAccessToken();
            String StackEndpoint="stacks"+"/"+StackName+"/"+StackID;
            //connect to endpoint
            String EndPointURL = "http://" + openstackServer + ":" + heatEndpointPort + "/" + heatApiVersion + "/" +tenantID+"/"+ StackEndpoint ;
            HttpURLConnection http = HTTPOpenstackRequest.BuildHTTPRequest(Token, EndPointURL, "DELETE");
            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
           
            }catch(Exception e){
                System.out.println("fail");
                    return "failed";
                
   }
        return "deleting";
}
      
      

}
