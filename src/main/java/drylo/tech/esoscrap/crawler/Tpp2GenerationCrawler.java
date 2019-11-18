package drylo.tech.esoscrap.crawler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import drylo.tech.esoscrap.model.Tpp2Generation;
import drylo.tech.esoscrap.repo.Tpp2DataRepository;
import drylo.tech.esoscrap.utils.Tpp2Identifier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class Tpp2GenerationCrawler {

    @Autowired
    Tpp2DataRepository tpp2Repo;

    @Scheduled(fixedRate = 1000 * 30, initialDelay = 10)
    public void crawlTpp2Website() {
        Tpp2Generation tpp2Generation = getPowerData();

        if(tpp2Generation == null) {
            tpp2Generation = getPowerData();
        }
        if(tpp2Generation == null) {
            tpp2Generation = getPowerData();
        }
        if(tpp2Generation != null) {
            tpp2Repo.save(tpp2Generation);
        }
    }

    public static Tpp2Generation getPowerData() {
        try {
            Document document = Jsoup.connect("https://www.tpp2.com/dispatch/loadrealtimedata.php").get();
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(document.text(), JsonArray.class);
            Map<Tpp2Identifier, Integer> powerValue = new HashMap<>();
            Map<Tpp2Identifier, Double> desulphData = new HashMap<>();

            int i = 0;
            for (JsonElement jsonData : jsonArray) {
                JsonObject jsonObj = jsonData.getAsJsonObject();
                int id = jsonObj.get("ID").getAsInt();

                if (id >= 619 && id <= 626) {
                    Tpp2Identifier identifier = Tpp2Identifier.valueOf(id);
                    int value = jsonObj.get("Value").getAsInt();
                    powerValue.put(identifier, value);
                } else if (id >= 627 && id <= 630 || id == 695) {
                    Tpp2Identifier identifier = Tpp2Identifier.valueOf(id);
                    double value = 0d;
                    try {
                        value = jsonObj.get("Value").getAsDouble();
                    } catch (NumberFormatException nfe) {
                        // No value, so fuck off
                    }
                    desulphData.put(identifier, value);
                }
            }
            return new Tpp2Generation(LocalDateTime.now(), powerValue, desulphData);
        } catch (Exception e) {
            return null;
        }
    }
}
