package drylo.tech.esoscrap;

import drylo.tech.esoscrap.model.Tpp2Generation;
import drylo.tech.esoscrap.crawler.Tpp2GenerationCrawler;
import org.junit.Test;

public class Tpp2Crawler {

    @Test
    public void test() {
        Tpp2Generation tpp2Generation = Tpp2GenerationCrawler.getPowerData();
        System.out.println(tpp2Generation);
    }


}
