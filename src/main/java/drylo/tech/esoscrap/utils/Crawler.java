package drylo.tech.esoscrap.utils;

import drylo.tech.esoscrap.model.PowerGeneration;
import org.influxdb.dto.Point;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class Crawler {

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
            Point point = InfluxUtils.powerGenerationToPoint(powerGeneration);
            InfluxUtils.savePoint(point);
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

            return new PowerGeneration(Instant.now(), data);
        } catch (Exception e) {
            System.err.println("Exception while parsing ESO website :"+ e.getMessage());
            return null;
        }
    }
}
