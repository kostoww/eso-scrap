package drylo.tech.esoscrap.utils;

import drylo.tech.esoscrap.model.PowerGeneration;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDBIOException;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfluxUtils {

    public static InfluxDB influxDB;

    static {
        checkConnection();
    }


    public static Point powerGenerationToPoint(PowerGeneration powerGeneration) {
        return Point.measurement("powerGeneration")
                .time(powerGeneration.getTime().toEpochMilli(), TimeUnit.MILLISECONDS)
                .addField("npp", powerGeneration.getNuclearPowerPlant())
                .addField("tpp", powerGeneration.getThermalPowerPlant())
                .addField("dhp", powerGeneration.getDistrictHeatingPlant())
                .addField("fpp", powerGeneration.getFabricPowerPlant())
                .addField("wpp", powerGeneration.getWaterPowerPlant())
                .addField("small-wpp", powerGeneration.getSmallWaterPowerPlant())
                .addField("wind-pp", powerGeneration.getWindPowerPlant())
                .addField("photo-pp", powerGeneration.getPhotovoltaicPowerPlant())
                .addField("bio-pp", powerGeneration.getBioPowerPlant())
                .build();
    }

    private static void checkConnection() {
        boolean isDBOnline;
        do {
            isDBOnline = connect();
        } while(!isDBOnline);
    }

    private static boolean connect() {
        influxDB = InfluxDBFactory.connect("http://localhost:8086");
        Pong response = null;
        try {
            response = influxDB.ping();
        } catch (InfluxDBIOException e) {
            System.err.println("Connection to InfluxDB cannot be accessed!" + e.getMessage());
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return false;
        }
        if(response != null && response.getVersion().equalsIgnoreCase("unknown")) {
            System.err.println("Connection to InfluxDB cannot be accessed!");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            checkDatabase();
            return true;
        }
    }

    private static void checkDatabase() {
        if(!influxDB.databaseExists("tpp2")) {
            influxDB.createDatabase("tpp2");
            influxDB.createRetentionPolicy(
                    "defaultPolicy", "tpp2", "30d", 1, true);
        }


    }

    public static void savePoint(Point point) {
        checkConnection();
        influxDB.write("tpp2", "defaultPolicy", point);
        System.out.println("Saved point" + point);
    }

    public static List<PowerGeneration> fetchAll(String db) {
        QueryResult queryResult = influxDB.query(new Query("Select * from powerGeneration", db));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<PowerGeneration> powerGenerationList = resultMapper
                .toPOJO(queryResult, PowerGeneration.class);

        return powerGenerationList;
    }
}
