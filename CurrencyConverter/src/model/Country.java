package model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// represents a country with conversion rate between its currency and cad
public class Country {

    private String currencyName;
    private double rateToCAD;
    private double rateFromCAD;

    // constructor
    public Country(String name) {
        this.currencyName = name;

        if (name.equals("CAD")) {
            this.rateToCAD = 1.0;
            this.rateFromCAD = 1.0;
        } else {
            try {
                URL url = new URL("https://www.bankofcanada.ca/valet/observations/group/FX_RATES_DAILY?recent=1");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new RuntimeException("Response code:" + responseCode);
                } else {
                    StringBuilder infoString = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        infoString.append(scanner.nextLine());
                    }
                    scanner.close();

                    JSONParser parser = new JSONParser();
                    org.json.simple.JSONObject jsonSimpleObject = (org.json.simple.JSONObject) parser.parse(String.valueOf(infoString));

                    net.sf.json.JSONObject jsonLibObject = net.sf.json.JSONObject.fromObject(jsonSimpleObject.toJSONString());

                    JSONArray exchangeRates = (JSONArray) jsonLibObject.get("observations");
                    for (int i = 0; i < exchangeRates.toArray().length; i++){
                        JSONObject first = (JSONObject) exchangeRates.get(i);
                        JSONObject currency = first.getJSONObject("FX" + this.currencyName + "CAD");
                        if (!currency.isNullObject()) {
                            String rate = String.valueOf(currency.get("v"));
                            this.rateToCAD = Double.parseDouble(rate);
                            this.rateFromCAD = 1/this.rateToCAD;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Double convert(double amount, Country convertToCountry) {
        return amount * this.rateToCAD * convertToCountry.getRateFromCAD();
    }


    // getters
    public String getCurrencyName() {
        return this.currencyName;
    }

    public double getRateToCAD() {
        return this.rateToCAD;
    }

    public double getRateFromCAD() {
        return this.rateFromCAD;
    }

}
