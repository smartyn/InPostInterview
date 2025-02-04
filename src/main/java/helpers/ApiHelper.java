package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ParcelLocker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ApiHelper {

    public void saveParcelLockerToFile(String city, List<ParcelLocker> lockers) {
        ObjectMapper mapper = new ObjectMapper();
        String parcelLockerFileName = "parcellockers." + city.replaceAll(" ", "_") + ".json";

        Path path = Paths.get("src", "test", "resources", "files");
        Path filePath = path.resolve(parcelLockerFileName);
        try {
            Files.write(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lockers).getBytes());
            System.out.println("Data saved in file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
