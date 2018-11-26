package Template;

import helpers.AccessToken;
import helpers.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.Vector;
import org.json.JSONObject;

/**
 *
 * @author yassine
 */
public class RSAKeyGen {

   
    public static final String ENDPOINT = "os-keypairs";
    
    private String openstackServerIP;
    private String endpointPort;
    private String novaVersion;
    private String rescueKeyDir;
    
    public RSAKeyGen(){
        Vector<String> Parameters = new Vector();
        Parameters.add("OPENSTACK_SERVER_IP");
        Parameters.add("NOVA_API_PORT");
        Parameters.add("NOVA_API_VERSION");
        Parameters.add("RSA_KEYS_DIR");
        
        Vector<String> params = Config.getProperties(Parameters);
        this.openstackServerIP = params.get(0);
        this.endpointPort= params.get(1);
        this.novaVersion= params.get(2);
        this.rescueKeyDir= params.get(3);
        
    }
    public  String generateKeyUUID() {
        UUID keyUUID = UUID.randomUUID();
        return "key-" + keyUUID.toString();

    }
    public String CreateKeyStoreDir(){
        String HOME =System.getProperty("user.home");
        if(new File(rescueKeyDir).exists() && new File(rescueKeyDir).isDirectory()){
            return rescueKeyDir;
        }else if(new File(HOME+"/OpenstackKeys/").mkdir() || new File(HOME+"/OpenstackKeys/").exists()) {
            
            return rescueKeyDir;
        }
        return "/tmp/";
    }
    public void RevokeRSAKey(String KeyName){
        try {
            AccessToken token = new AccessToken();
            String Token = token.RequestAccessToken();
            String EndPointURL = "http://" + openstackServerIP + ":" + endpointPort + "/" + novaVersion + "/" + ENDPOINT+"/"+KeyName;
            URL url = new URL(EndPointURL);
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) connection;
            
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.setRequestProperty("X-OpenStack-Nova-API-Version", "2.53");
            http.setRequestProperty("X-Auth-Token", Token);
            http.setDoOutput(true);
            http.connect();
  
        } catch (Exception e) {
           System.out.println("An error occurred while revoking key: "+KeyName);
        }
    }
    public String GenerateStackKey(String KeyPairName) {
        try {
            AccessToken token = new AccessToken();
            String Token = token.RequestAccessToken();
            String EndPointURL = "http://" + openstackServerIP + ":" + endpointPort + "/" + novaVersion + "/" + ENDPOINT;
            URL url = new URL(EndPointURL);
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) connection;
            http.setRequestMethod("POST");
            String HTTPRequestBody = "{\"keypair\": {\"name\": \""+KeyPairName+"\",\"type\": \"ssh\"\n }}";
            byte[] bson = HTTPRequestBody.getBytes(StandardCharsets.UTF_8);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //X-OpenStack-Nova-API-Version: 2.53
            http.setRequestProperty("X-OpenStack-Nova-API-Version", "2.53");
            http.setRequestProperty("X-Auth-Token", Token);
            http.setDoOutput(true);
            http.connect();
            OutputStream os = http.getOutputStream();
            os.write(bson);

            InputStream is = http.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject KeyPair = new JSONObject(sb.toString());
            String PrivateKey = KeyPair.getJSONObject("keypair").get("private_key").toString();
          return PrivateKey;
        } catch (Exception e) {
            return null;
        }

    }
}
