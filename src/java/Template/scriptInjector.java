/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author yassine
 */
public class scriptInjector {

    private String HOTTemplate;
    public static final String SCRIPT = "#!/bin/bash\n" +
"sudo route delete default gw 10.10.0.1 eth1\n" +
"sudo route delete default gw 10.0.0.1 eth0\n" +
"sudo route add default gw 10.0.0.1 metric 1 eth0\n" +
"sudo route add default gw 10.10.0.1 metric 10 eth1\n" +
"sudo echo '1 rt2' >> /etc/iproute2/rt_tables\n" +
"sudo echo '2 rt3' >> /etc/iproute2/rt_tables\n" +
"SECOND_INTERFACE=$(ip a| grep inet | awk {'print $2'} | grep 10.10.0 | awk -F/ {'print $1'} )\n" +
"FIRST_INTERFACE=$(ip a| grep inet | awk {'print $2'} | grep 10.0.0 | awk -F/ {'print $1'} )   \n" +
"sudo ip route add 10.10.0.0/16 dev eth1 src $SECOND_INTERFACE table rt2\n" +
"sudo ip route add 10.0.0.0/8 dev eth0 src $FIRST_INTERFACE table rt3\n" +
"sudo ip route add default via 10.10.0.1 dev eth1 table rt2\n" +
"sudo ip route add default via 10.0.0.1 dev eth0 table rt3\n" +
"sudo ip rule add from $SECOND_INTERFACE/32 table rt2\n" +
"sudo ip rule add from $FIRST_INTERFACE/32 table rt3\n" +
"sudo ip rule add to $SECOND_INTERFACE/32 table rt2\n" +
"sudo ip rule add to $FIRST_INTERFACE/32 table rt3\n" +
"sudo ip link set mtu 1400 dev eth1\n"+
"sudo ip route add 192.168.255.0/24 via 10.0.0.1\n"+
"sudo ip route add 172.31.0.0/16 via 10.10.0.1\n";

    public scriptInjector(String HOTTemplate) {
        this.HOTTemplate = HOTTemplate;
    }

    public void InsertBashScript(String FinalTemplate) {
        try {
            FileInputStream fis = new FileInputStream(HOTTemplate);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            PrintWriter writer = new PrintWriter(FinalTemplate, "UTF-8");
            String Line;

            while ((Line = br.readLine()) != null) {

                if (Line.contains("user_data:")) {
                    Line = Line.replace("user_data: |", SCRIPT);
                }
                writer.write(Line);
                writer.write("\n");
            }

            br.close();
            writer.close();
            //remove intermediary files
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
