package drylo.tech.esoscrap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tpp2Crawler {

    @Test
    public void test() {
        Map<Tpp2Identifier, Integer> powerUnits = getPowerUnits();
    }

    public Map<Tpp2Identifier, Integer> getPowerUnits()  {
        try {
            Document document = Jsoup.connect("https://www.tpp2.com/dispatch/loadrealtimedata.php").get();
            JSONArray jsonData = new JSONArray(document.text());

            int[] values = new int[8];
            Map<Tpp2Identifier, Integer> powerValue = new HashMap<>();
            int i = 0;
            for (Object jsonDatum : jsonData) {
                JSONObject jsonObject = (JSONObject) jsonDatum;
                int id = (int) jsonObject.get("ID");
                if (id >= 619 && id <= 626) {
                    Tpp2Identifier identifier = Tpp2Identifier.valueOf(id);
                    int value = (int) jsonObject.get("Value");
                    System.out.println(identifier + " " + value);
                    powerValue.put(identifier, value);
                }
            }
            return powerValue;
        } catch (Exception e) {
            return null;
        }
    }

}
