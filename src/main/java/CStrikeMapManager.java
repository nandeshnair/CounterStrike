import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CStrikeMapManager {
    private final List<String> mapNames;
    private final String configFilePath;

    public CStrikeMapManager(String configFilePath) {
        this.configFilePath = configFilePath;
        this.mapNames = new LinkedList<>();
    }

    public void run() throws IOException {
        readMapCycleConfig();
        shuffleMapOrder();
        exportMappingCycleConfig();
    }
    private void readMapCycleConfig() throws IOException {
        FileReader reader = new FileReader(configFilePath);
        BufferedReader br = new BufferedReader(reader);
        String mapName;
        while ((mapName = br.readLine()) != null) {
            mapNames.add(mapName);
        }
        reader.close();
    }
    private void exportMappingCycleConfig() throws IOException {
        FileWriter writer = new FileWriter(configFilePath);
        for(String mapName: mapNames) {
            writer.write(mapName + System.lineSeparator());
        }
        writer.close();
    }
    private void shuffleMapOrder() {
        Collections.shuffle(this.mapNames);
    }
    public static void main(String[] args) {
        CStrikeMapManager mapManager = new CStrikeMapManager(args[0]);
        try {
            //java -cp "$HOME" CStrikeMapManager $HOME/hlds/cstrike/mapcycle.txt
            mapManager.run();
            System.out.println("Map shuffle successful!");
        } catch (IOException e) {
            System.err.println("Couldn't shuffle maps.");
            e.printStackTrace();
        }
    }
}
