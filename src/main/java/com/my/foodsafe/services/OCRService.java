package com.my.foodsafe.services;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OCRService implements IOCRService {

    private final RestTemplate restTemplate;

    public OCRService() {
        this.restTemplate = new RestTemplate();
    }

    public String processOCR(MultipartFile file) throws IOException {
        GoogleCredentials credentials;
        Map<String, List<String>> FoodContent = new HashMap<>();
        Queue<String> nutrition = new LinkedList<>();
        Queue<String> amount = new LinkedList<>();
        List<String> food = Arrays.asList("熱量", "蛋白質", "。", "營養標示");
        try (FileInputStream credentialsStream = new FileInputStream("C:\\Users\\danie\\Downloads\\citric-expanse-425013-h9-c8180a69bb40.json")) {
            credentials = GoogleCredentials.fromStream(credentialsStream);
        }

        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create(settings)) {
            ByteString imgBytes = ByteString.copyFrom(file.getBytes());
            Image image = Image.newBuilder().setContent(imgBytes).build();
            Feature feature = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image).build();

            List<AnnotateImageRequest> requests = new ArrayList<>();
            requests.add(request);

            BatchAnnotateImagesResponse responses = client.batchAnnotateImages(requests);
            AnnotateImageResponse response = responses.getResponsesList().get(0);

            String ocrText = response.getTextAnnotationsList().isEmpty() ? "" : response.getTextAnnotationsList().get(0).getDescription();

            Pattern name = Pattern.compile("名:\\s*([^\\n/]+)");
            Matcher matcher = name.matcher(ocrText);
            String productName = "";
            if (matcher.find()) {
                productName = matcher.group(1);
            }

            //找字串中的原料
            int startIndex = -1;
            startIndex = ocrText.indexOf("原料:");
            if (startIndex == -1) {
                startIndex = ocrText.indexOf("成分:");
            }
            String ingredients = searchAndExtract(ocrText, startIndex, food);

            //營養成分
//            int ptr = ocrText.indexOf("營養標示");
//            nutrition.add("熱量");nutrition.add("蛋白質");nutrition.add("脂肪");nutrition.add("飽和脂肪");
//            nutrition.add("反式脂肪");nutrition.add("碳水化合物");nutrition.add("鈉");
//            String temp = "";
            Queue<String> labels = new LinkedList<>();
            Queue<String> values = new LinkedList<>();

            String[] lines = ocrText.split("\n");
            for (String line : lines) {
                line = line.trim();
                String[] strings = line.split(" ");
                for (String string : strings) {
                    if (Character.isDigit(string.charAt(0))) {
                        values.offer(string);
                    } else {
                        labels.offer(string);
                    }
                }

            }
            while (!labels.isEmpty() && !values.isEmpty()) {
                String label = labels.poll();
                Double value1 = Double.parseDouble(values.poll().replaceAll("[大卡公克毫克]", ""));
                Double value2 = Double.parseDouble(values.poll().replaceAll("[大卡公克毫克]", ""));
                System.out.println(label + ": " + value1 + " " + value2);
                return productName;
            }

        }
        return "ss";
    }
    //從字串中分離關鍵字
    public static String searchAndExtract(String source, int startIndex, List<String> keywords) {
        // 確認開始索引在範圍內
        if (startIndex < 0 || startIndex >= source.length()) {
            throw new IllegalArgumentException("Start index is out of bounds.");
        }

        // 初始化變量來儲存最早出現的關鍵字索引
        int earliestIndex = -1;
        int keywordLength = 0;

        // 遍歷所有關鍵字，找到最早出現的索引
        for (String keyword : keywords) {
            int index = source.indexOf(keyword, startIndex);
            if (index != -1 && (earliestIndex == -1 || index < earliestIndex)) {
                earliestIndex = index;
                keywordLength = keyword.length();
            }
        }

        // 如果找不到任何關鍵字，返回空字串
        if (earliestIndex == -1) {
            return "";
        }

        // 提取從開始索引到最早出現的關鍵字位置之間的子字串
        return source.substring(startIndex, earliestIndex + keywordLength);
    }
}
