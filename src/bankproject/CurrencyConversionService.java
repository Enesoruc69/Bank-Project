/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
import java.util.HashMap;
import java.util.Map;

public class CurrencyConversionService {
    private final Map<String, Double> exchangeRates;

    public CurrencyConversionService() {
        this.exchangeRates = new HashMap<>();
        initializeDefaultRates();
    }

    private void initializeDefaultRates() {
        exchangeRates.put("USD_TO_EUR", 0.9);
        exchangeRates.put("EUR_TO_USD", 1.1);
        exchangeRates.put("USD_TO_TRY", 35.0);
        exchangeRates.put("TRY_TO_USD", 0.035);
        exchangeRates.put("EUR_TO_TRY", 39.0);
        exchangeRates.put("TRY_TO_EUR", 0.033);
    }

    public void setExchangeRate(String conversionKey, double rate) {
        exchangeRates.put(conversionKey, rate);
        System.out.println(conversionKey + " için döviz kuru " + rate + " olarak ayarlandı.");
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        String conversionKey = fromCurrency + "_TO_" + toCurrency;

        if (!exchangeRates.containsKey(conversionKey)) {
            System.out.println("Dönüşüm yapılamıyor. Döviz kuru bulunamadı: " + conversionKey);
            return 0.0;
        }

        double rate = exchangeRates.get(conversionKey);
        double convertedAmount = amount * rate;

        System.out.println(amount + " " + fromCurrency + " -> " + convertedAmount + " " + toCurrency + " (Kur: " + rate + ")");
        return convertedAmount;
    }
}
