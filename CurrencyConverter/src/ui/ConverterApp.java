package ui;

import model.Country;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConverterApp extends JFrame implements ActionListener {

    private List<Country> countries;

    private JFrame frame;

    private java.awt.Button convertButton;
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private java.awt.Label label1;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;

    public ConverterApp() {
        runConverter();
    }

    // EFFECTS: runs converter application
    public void runConverter() {
        setUpCountries();
        setUpFrame();
        setUpComponents();
    }

    // EFFECTS: creates list of countries
    public void setUpCountries() {
        countries = new ArrayList<Country>();
        countries.add(new Country("AUD"));
        countries.add(new Country("BRL"));
        countries.add(new Country("CHF"));
        countries.add(new Country("CNY"));
        countries.add(new Country("CAD"));
        countries.add(new Country("EUR"));
        countries.add(new Country("GBP"));
        countries.add(new Country("HKD"));
        countries.add(new Country("IDR"));
        countries.add(new Country("JPY"));
        countries.add(new Country("KRW"));
        countries.add(new Country("MXN"));
        countries.add(new Country("MYR"));
        countries.add(new Country("NZD"));
        countries.add(new Country("NOK"));
        countries.add(new Country("PEN"));
        countries.add(new Country("RUB"));
        countries.add(new Country("SAR"));
        countries.add(new Country("SGD"));
        countries.add(new Country("SEK"));
        countries.add(new Country("THB"));
        countries.add(new Country("TRY"));
        countries.add(new Country("TWD"));
        countries.add(new Country("USD"));
        countries.add(new Country("VND"));
        countries.add(new Country("ZAR"));
    }

    // EFFECT: sets up frame
    public void setUpFrame() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(391, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setUpComponents() {
        // creates components
        JPanel panel = new JPanel();
        panel.setBackground(new Color(122,193,193));

        label1 = new java.awt.Label();
        choice1 = new Choice();
        choice2 = new Choice();
        for (Country c: countries) {
            choice1.add(c.getCurrencyName());
            choice2.add(c.getCurrencyName());
        }
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        convertButton = new java.awt.Button();
        convertButton.addActionListener(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Gadugi", 1, 30));
        label1.setText("Currency Converter");
        convertButton.setLabel("Convert");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(choice1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(160, 160, 160)
                                                .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(113, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("");

        pack();

        frame.add(panel);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent avt) {
        if (avt.getSource() == convertButton) {
            String t1 = textField1.getText();
            Double valueBeforeConversion = Double.parseDouble(t1);
            Country convertFromCountry = getCountryFromString(choice1.getSelectedItem());
            Country convertToCountry = getCountryFromString(choice2.getSelectedItem());
            Double valueAfterConversion = convertFromCountry.convert(valueBeforeConversion, convertToCountry);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            textField2.setText(df.format(valueAfterConversion));
        }
    }

    private Country getCountryFromString(String country) {
        for (Country c: countries) {
            if (country.equals(c.getCurrencyName())) {
                return c;
            }
        }
        return null;
    }
}