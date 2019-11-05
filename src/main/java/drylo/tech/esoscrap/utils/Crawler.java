package drylo.tech.esoscrap.utils;

import drylo.tech.esoscrap.model.PowerGeneration;
import drylo.tech.esoscrap.repo.PowerDataRepository;
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
    PowerDataRepository powerDataRepo;

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
        PowerGeneration powerGeneration = crawlPowerGeneration();

        if(powerGeneration != null) {
            powerDataRepo.save(powerGeneration);
        }
    }

    public static PowerGeneration crawlPowerGeneration() {
        try {
            Document page = Jsoup.connect("http://eso.bg/?did=32").get();

            Elements dataTable = page.getElementsByClass("defaultTable2");

            int i = 0;
            Integer[] data = new Integer[9];

            for (String source : POWER_SOURCE_LABELS) {
                String mw = dataTable.select("td:containsOwn(" + source + ")").next().first().text();
                data[i++] = Integer.parseInt(mw);
            }

            return new PowerGeneration(LocalDateTime.now(), data);
        } catch (Exception e) {
            System.err.println("Exception while parsing ESO website :"+ e.getMessage());
            return null;
        }
    }
}
