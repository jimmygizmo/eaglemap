package ai.smartmetal.eaglemap.seed;

import ai.smartmetal.eaglemap.model.Location;
import ai.smartmetal.eaglemap.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class LocationSeeder implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("LocationSeeder starting...");

        if (locationRepository.count() > 0) {
            System.out.println("Locations already seeded. Skipping.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("locations.tsv")),
                StandardCharsets.UTF_8))) {

            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                String[] fields = line.split("\t");
                if (fields.length < 16) continue; // skip malformed lines

                try {
                    String name = fields[1];
                    String asciiname = fields[2];
                    double latitude = Double.parseDouble(fields[4]);
                    double longitude = Double.parseDouble(fields[5]);
                    String country = fields[8];
                    double population = Double.parseDouble(fields[14]);

                    int elevation = 0;
                    if (!fields[15].isEmpty()) {
                        elevation = Integer.parseInt(fields[15]);
                    }

                    Location location = new Location(name, asciiname, latitude, longitude, country, population, elevation);
                    locationRepository.save(location);
                    count++;

                } catch (Exception e) {
                    System.err.println("Skipping line due to error: " + e.getMessage());
                }  // try - parse line
            }  // LOOP - lines

            System.out.println("Locations seeded. Count: " + count);
        }  // try - read file
    }  // run
}  // class LocationSeeder


// NOTES:

// source data file field map - file: locations.tsv
//        ---------------------------------------------------
//  0  geonameid         : integer id of record in geonames database
//  1  name              : name of geographical point (utf8) varchar(200)
//  2  asciiname         : name of geographical point in plain ascii characters, varchar(200)
//  3  alternatenames    : alternatenames, comma separated, ascii names automatically transliterated, convenience attribute from alternatename table, varchar(10000)
//  4  latitude          : latitude in decimal degrees (wgs84)
//  5  longitude         : longitude in decimal degrees (wgs84)
//  6  feature class     : see http://www.geonames.org/export/codes.html, char(1)
//  7  feature code      : see http://www.geonames.org/export/codes.html, varchar(10)
//  8  country code      : ISO-3166 2-letter country code, 2 characters
//  9  cc2               : alternate country codes, comma separated, ISO-3166 2-letter country code, 200 characters
// 10  admin1 code       : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
// 11  admin2 code       : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
// 12  admin3 code       : code for third level administrative division, varchar(20)
// 13  admin4 code       : code for fourth level administrative division, varchar(20)
// 14  population        : bigint (8 byte int)
// 15  elevation         : in meters, integer
// 16  dem               : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
// 17  timezone          : the iana timezone id (see file timeZone.txt) varchar(40)
// 18  modification date : date of last modification in yyyy-MM-dd format

