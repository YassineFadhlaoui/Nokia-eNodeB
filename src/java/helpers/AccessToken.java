package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author yassine
 */
public class AccessToken {

    private String openstackServer;
    private String keystonePort;
    private String keystoneTokenUri;
    private String adminProject;
    private String identityMethod;
    private String username;
    private String domain;
    private String openstackTokenFile;
    private String adminPassword;
//clean
    public AccessToken() {
        Vector<String> Parameters = new Vector();
        Parameters.add("OPENSTACK_SERVER_IP");
        Parameters.add("KEYSTONE_PORT");
        Parameters.add("KEYSTONE_TOKEN_URI");
        Parameters.add("ADMIN_PROJECT");
        Parameters.add("IDENTITY_METHOD");
        Parameters.add("USERNAME");
        Parameters.add("DOMAIN");
        Parameters.add("OPENSTACK_TOKEN_FILE");
        Parameters.add("ADMIN_PASSWORD");

        Vector<String> params = Config.getProperties(Parameters);
        this.openstackServer = params.get(0);
        this.keystonePort = params.get(1);
        this.keystoneTokenUri = params.get(2);
        this.adminProject = params.get(3);
        this.identityMethod = params.get(4);
        this.username = params.get(5);
        this.domain = params.get(6);
        this.openstackTokenFile = params.get(7);
        this.adminPassword = params.get(8);
    }

    private String buildOPenstackTokenRequest() {
        String Auth = "{ \"auth\": {\n"
                + "    \"identity\": {\n"
                + "      \"methods\": [\"" + identityMethod + "\"],\n"
                + "      \"password\": {\n"
                + "        \"user\": {\n"
                + "          \"name\": \"" + username + "\",\n"
                + "          \"domain\": { \"id\": \"" + domain + "\" },\n"
                + "          \"password\": \"" + adminPassword + "\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"scope\": {\n"
                + "      \"project\": {\n"
                + "        \"name\": \"" + adminProject + "\",\n"
                + "        \"domain\": { \"id\": \"" + domain + "\" }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}";
        return Auth;
    }

    private String ConvertToJson(String Auth) throws ParseException {
        JSONParser JsonParser = new JSONParser();
        Object JsonObject = JsonParser.parse(Auth);
        String StringifiedObject = String.valueOf(JsonObject);
        return StringifiedObject;
    }

    private HttpURLConnection BuildHTTPRequest(String Auth) throws ProtocolException, MalformedURLException, IOException {
        String Keystone_URL = "http://" + openstackServer + ":" + keystonePort + keystoneTokenUri;
        URL url = new URL(Keystone_URL);
        URLConnection connection = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) connection;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        byte[] bson = Auth.getBytes(StandardCharsets.UTF_8);
        int RequestLength = bson.length;

        http.setFixedLengthStreamingMode(RequestLength);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(bson);
        }
        return http;
    }

    private String getRequestedToken(HttpURLConnection http) {
        String token = http.getHeaderField("X-Subject-Token");
        return token;
    }

    private void SerializeToken(String accessToken) {
        long creationTime = System.currentTimeMillis();
        Token token = new Token(accessToken, creationTime);
        ObjectOutputStream oos = null;
        try {
            FileOutputStream SerializedToken = new FileOutputStream(openstackTokenFile);
            oos = new ObjectOutputStream(SerializedToken);
            oos.writeObject(token);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean isTokenValid(long creationTime) throws IOException, FileNotFoundException, ClassNotFoundException {
        long CurrentTime = System.currentTimeMillis();
        long Delta = CurrentTime - creationTime;
        boolean isValid = (Delta > 378000) ? false : true;
        return isValid;

    }

    private Token DeserializeToken() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(openstackTokenFile);
        ObjectInputStream ois = new ObjectInputStream(fi);
        Token token = (Token) ois.readObject();
        return token;
    }

    private String RequestNewAccessToken() throws ParseException, MalformedURLException, IOException {
        String Auth = buildOPenstackTokenRequest();
        String JSON = ConvertToJson(Auth);
        HttpURLConnection HttpConnection = BuildHTTPRequest(JSON);
        String Token = getRequestedToken(HttpConnection);
        SerializeToken(Token);
        return Token;
    }

    public String RequestAccessToken() throws ParseException, MalformedURLException, IOException, FileNotFoundException, ClassNotFoundException {

        File tokenFile = new File(openstackTokenFile);
        if (tokenFile.exists()) {
            Token oldToken = DeserializeToken();
            if (isTokenValid(oldToken.getCreationTime())) {
       
                return oldToken.getToken();
            } else {
     
                String token = RequestNewAccessToken();
                return token;
            }
        } else {
            String token = RequestNewAccessToken();
            return token;

        }

    }

}
