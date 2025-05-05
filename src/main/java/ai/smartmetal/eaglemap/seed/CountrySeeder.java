package ai.smartmetal.eaglemap.seed;

import ai.smartmetal.eaglemap.model.Country;
import ai.smartmetal.eaglemap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class CountrySeeder implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CountrySeeder starting...");

        if (countryRepository.count() > 0) {
            System.out.println("Country data already seeded. Skipping.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("countries.tsv")),
                StandardCharsets.UTF_8))) {

            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                String[] fields = line.split("\t");

                // TODO: Nail down this field length check after we address the issue with parsing country population,
                // which is at position 15ish. PROBLEM: The file looks like it might have variable count of delimiters
                // at some point to the right side of the record so the end of records where country population is
                // is the most quesstionable. We had a total failure of parsing when we included fields at position 15.
                // Once that issue is solved, we can set the field count check precisely.
                // Until then, a good strategy is just to set it to the highest index we actually NEED at the moment.
                // Take into account the zero-based index. We need up to index 4 so thats a count of 5.
                if (fields.length < 5) continue; // skip malformed lines

                try {
                    String name = fields[4];
                    String code = fields[0];

//                    int population = 0;
//                    if (!fields[15].isEmpty()) {
//                        population = Integer.parseInt(fields[15]);
//                    }
//                    int population = 0;  // TEST - RESULTS: Confirmed this field has a problem in the source data.

//                    Country country = new Country(name, code, population);
                    Country country = new Country(name, code);
                    countryRepository.save(country);
                    count++;

                } catch (Exception e) {
                    System.err.println("Skipping line due to error: " + e.getMessage());
                }  // try - parse line
            }  // LOOP - lines

            System.out.println("Country data seeded. Count: " + count);
        }  // try - read file
    }  // run
}  // class CountrySeeder


/*


 NOTES:
 Location source data file is the GeoName file: countryInfo.txt
 Name of (unmodified) file in EagleMap project: countries.tsv
 Latest downloads (exports/dumps from GeoName):
 https://download.geonames.org/export/dump/
 Schema / Field Definitions:

 0 ISO    *
 1 ISO3
 2 ISO-Numeric
 3 fips
 4 Country    *
 5 Capital
 6 Area(in sq km)
 7 Population    **** Plan to use but has issues in the current source data so left out of seed for now.
 8 Continent
 9 tld
10 CurrencyCode
11 CurrencyName
12 Phone
13 Postal
14 Code
15 Format
16 Postal
17 Code
18 Regex
19 Languages
20 geonameid
21 neighbours
22 EquivalentFipsCode

* indicates those currently used in EagleMap
*/

