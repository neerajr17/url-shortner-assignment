package com.infracloud.assignment.url.shortner.service.impl;

import com.google.common.hash.Hashing;
import com.infracloud.assignment.url.shortner.service.UrlShortnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UrlShortnerServiceImpl implements UrlShortnerService {

    final static String outputFilePath = System.getProperty("user.dir") + "/url_path_file.txt";

    @Override
    public String getUrlByKey(@NotBlank String hash) {
        Map<String, String> mapFromFile = hashMapFromTextFile();
        return mapFromFile.get(hash);
    }

    public static Map<String, String> hashMapFromTextFile() {
        Map<String, String> map = new HashMap<>();
        BufferedReader br = null;
        try {
            File file = new File(outputFilePath);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            // read file line by line
            while ((line = br.readLine()) != null) {
                // splitting the line by :
                String[] parts = line.split(",");
                // first part is hash id, second is url
                String id = parts[0].trim();
                String url = parts[1].trim();
                if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(id))
                    map.put(id, url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }
        return map;
    }

    @Override
    public String shortenUrl(String url) {
        File file = new File(outputFilePath);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();// creating hash id for url

            Map<String, String> map = new HashMap<>();
            map.put(id, url);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            return id;
        } catch (Exception e) {
            log.error("Exception while storing to Redis Service", e.getMessage());
            return "";
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
            }
        }
    }

}

