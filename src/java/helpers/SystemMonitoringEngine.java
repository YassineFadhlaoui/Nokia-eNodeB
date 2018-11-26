package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yassine
 */
public class SystemMonitoringEngine {

    public SystemMonitoringEngine() {
    }

    public static String CPUAverage() throws IOException {
        List<String> command = new ArrayList<String>();
        command.add("cat");
        command.add("/proc/loadavg");

        Process process = new ProcessBuilder(command).start();
        InputStream is = process.getInputStream();
        String line;
        String cpuAverage = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            cpuAverage = line.replaceAll("\\s+", ";").trim();
            cpuAverage = cpuAverage.split(";")[0];
        }
        return cpuAverage;
    }

    public static String MemoryProcess() throws IOException {
        List<String> command = new ArrayList<String>();
        command.add("free");

        Process process = new ProcessBuilder(command).start();
        InputStream is = process.getInputStream();
        String line;
        String Memory = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Mem:")) {
                Memory = line.replaceAll("\\s+", " ").trim();
            }
        }

        return Memory;
    }
}
