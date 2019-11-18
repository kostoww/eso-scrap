package drylo.tech.esoscrap.utils;

import drylo.tech.esoscrap.model.NuclearGeneration;
import drylo.tech.esoscrap.model.PowerGeneration;
import drylo.tech.esoscrap.repo.NuclearDataRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NuclearGenerationCrawler {

    @Autowired
    NuclearDataRepository nuclearRepo;

    @Scheduled(fixedRate = 1000 * 30, initialDelay = 0)
    public void crawlEsoWebsite() {
        NuclearGeneration nuclearGeneration = getNuclearPowerValues();

        nuclearRepo.save(nuclearGeneration);

    }

    public static NuclearGeneration getNuclearPowerValues() {
        NuclearGeneration nuclearGeneration = new NuclearGeneration();
        try {
            Document document = Jsoup.connect("http://www.npp.bg:8081/InternetParameters.aspx").get();

            Element blockFiveElement = document.getElementById("txt_bl5_powervalue");
            int blockFive = Integer.parseInt(blockFiveElement.attr("value").replace(" MW", ""));
            nuclearGeneration.setFive(blockFive);

            Element blockSixElement = document.getElementById("txt_bl6_powevalue");
            int blockSix = Integer.parseInt(blockSixElement.attr("value").replace(" MW", ""));
            nuclearGeneration.setSix(blockSix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuclearGeneration;
    }
}
