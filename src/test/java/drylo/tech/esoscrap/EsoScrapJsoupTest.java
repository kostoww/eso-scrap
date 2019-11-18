package drylo.tech.esoscrap;

import drylo.tech.esoscrap.model.PowerGeneration;
import drylo.tech.esoscrap.crawler.PowerGenerationCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class EsoScrapJsoupTest {

    private int[] POWER_SOURCE_MW = new int[9];
    private double[] POWER_SOURCE_PERCENTAGE = new double [9];

    @Test
    public void test() throws IOException {
        Document page = Jsoup.connect("http://eso.bg/?did=32").get();

        Elements dataTable = page.getElementsByClass("defaultTable2");
        Assert.assertEquals(1, dataTable.size());

        Elements tableRows = dataTable.first().select("tr");
        Assert.assertEquals( 11, tableRows.size());


        int i = 0;
        int sum = 0;
        Integer[] data = new Integer[10];

        for(String source : PowerGenerationCrawler.POWER_SOURCE_LABELS) {
            Element mwElement = dataTable.select("td:containsOwn(" + source + ")").next().first();
            String mw = mwElement.text();
            int currentPower = Integer.parseInt(mw);
            sum += POWER_SOURCE_MW[i] = currentPower;
            data[i] = currentPower;
            POWER_SOURCE_PERCENTAGE[i++] = Double.parseDouble(mwElement.nextElementSibling().nextElementSibling().text().replace("%", ""));
        }

        String consumption = "Товар на РБ";
        int consumation = Integer.parseInt(dataTable.select("th:containsOwn(" + consumption + ")").next().first().text());
        int export = sum - consumation;
        int j = 0;
        for(double percent : POWER_SOURCE_PERCENTAGE) {
            double part = (double) POWER_SOURCE_MW[j++] / sum * 100;
//            part = Math.round(part * 100.0) / 100.0;
            Assert.assertEquals(percent, part, 2d);
        }

        PowerGeneration powerGeneration = new PowerGeneration(LocalDateTime.now(), data);
    }

    @Test
    public void testCrawler() {
        PowerGenerationCrawler.crawlPowerGeneration();
    }
}
