/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author yassine
 */
//clean 
public class HTTPOpenstackRequest {

    public static HttpURLConnection BuildHTTPRequest(String Token, String FullURL, String Method) throws ProtocolException, MalformedURLException, IOException {
        URL url = new URL(FullURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) connection;
        http.setRequestMethod(Method);
        http.setRequestProperty("X-Auth-Token", Token);
        http.setDoOutput(true);
        http.connect();

        return http;
    }

}
