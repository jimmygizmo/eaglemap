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
// DISABLED TO TEST
//                if (fields.length < 22) continue; // skip malformed lines
                // TODO: Confirm/tune this field length check. Especially with country data. Variable field population noted.

                try {
                    String name = fields[4];
                    String code = fields[0];

//                    int population = 0;
//                    if (!fields[15].isEmpty()) {
//                        population = Integer.parseInt(fields[15]);
//                    }
                    int population = 0;  // TEST

                    Country country = new Country(name, code, population);
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
 source data file field map - file: countries.tsv
        ---------------------------------------------------
Country Info source schema:

00 ISO    *
01 ISO3
02 ISO-Numeric
03 fips
04 Country    *
05 Capital
06 Area(in sq km)
07 Population    *
08 Continent
09 tld
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

