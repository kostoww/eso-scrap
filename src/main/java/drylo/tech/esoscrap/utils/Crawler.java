package drylo.tech.esoscrap.utils;

import drylo.tech.esoscrap.model.PowerData;
import drylo.tech.esoscrap.repo.PowerDataRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Crawler {

    @Autowired
    PowerDataRepo repo;

    public static String[] POWER_SOURCE_LABELS = new String[] {
            "АЕЦ",
            "Кондензационни ТЕЦ",
            "Топлофикационни ТЕЦ",
            "Заводски ТЕЦ",
            "ВЕЦ",
            "Малки ВЕЦ",
            "ВяЕЦ",
            "ФЕЦ",
            "Био ТЕЦ"
    };

    @Scheduled(fixedRate = 1000 * 30, initialDelay = 0)
    public void crawlEsoWebsite() {
        PowerData powerData = crawlPowerGeneration();

        repo.save(powerData);
    }

    public static PowerData crawlPowerGeneration() {
        try {
            Document page = Jsoup.connect("http://eso.bg/?did=32").get();

            Elements dataTable = page.getElementsByClass("defaultTable2");

            int i = 0;
            Integer[] data = new Integer[9];
            int sum = 0;
            for (String source : POWER_SOURCE_LABELS) {
                String mw = dataTable.select("td:containsOwn(" + source + ")").next().first().text();
                sum += data[i++] = Integer.parseInt(mw);

            }
            String consumption = "Товар на РБ";
            int consumation = Integer.parseInt(dataTable.select("th:containsOwn(" + consumption + ")").next().first().text());
            int export = sum - consumation;
            return new PowerData(LocalDateTime.now(), export, data);
        } catch (Exception e) {
            System.err.println("Exception while parsing ESO website :"+ e.getMessage());
            return null;
        }
    }
}
