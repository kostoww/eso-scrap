package drylo.tech.esoscrap;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class EntsoeCrawler {

    @Test
    public void test() {
        final String uri = "https://transparency.entsoe.eu/api";
//                "securityToken=72e01724-65a4-4fcb-86ed-3debbf876a85&" +
//                "in_domain=10YCA-BULGARIA-R&" +
//                "documentType=A44&" +
//                "periodStart=201512312300&" +
//                "periodEnd=201612312300&" +
//                "out_domain=10YCA-BULGARIA-R";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(uri)
                // Add query parameter
                .queryParam("securityToken", "72e01724-65a4-4fcb-86ed-3debbf876a85")
                .queryParam("in_domain", "10YCA-BULGARIA-R")
                .queryParam("out_domain", "10YCA-BULGARIA-R")
                .queryParam("documentType", "A44")
                .queryParam("periodStart", "201612280000")
                .queryParam("periodEnd", "201612312300");
        System.out.println(builder.toUriString());
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(builder.toUriString(), String.class);

        System.out.println(result);
    }
}
