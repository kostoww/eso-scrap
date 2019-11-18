package drylo.tech.esoscrap;

import drylo.tech.esoscrap.model.NuclearGeneration;
import drylo.tech.esoscrap.crawler.NuclearGenerationCrawler;
import org.junit.Assert;
import org.junit.Test;

public class NuclearPowerCrawlerTest {
    @Test
    public void crawl() {
        NuclearGeneration nuclearPowerValues = NuclearGenerationCrawler.getNuclearPowerValues();
        Assert.assertNotEquals(0, nuclearPowerValues.getFive());
        Assert.assertNotEquals(0, nuclearPowerValues.getSix());
    }

}
