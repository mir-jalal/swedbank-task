package com.swedbank.datagenerator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.datagenerator.model.BitcoinPriceIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class BitcoinPriceIndexGeneratorService {

    public final ObjectMapper objectMapper;
    private static final String BITCOIN_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36";

    public BitcoinPriceIndex getBitcoinPriceIndex() throws IOException {
        URL url = new URL(BITCOIN_URL);
        HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        InputStream input = myURLConnection.getInputStream();

        BitcoinPriceIndex bitcoinPriceIndex = objectMapper.readValue(input, BitcoinPriceIndex.class);

        System.out.println(bitcoinPriceIndex);

        return bitcoinPriceIndex;
    }
}
