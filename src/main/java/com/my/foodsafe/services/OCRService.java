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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        try (FileInputStream credentialsStream = new FileInputStream("C:\\Users\\USER\\Desktop\\FoodSafe-master-master-master\\serious-cabinet-417111-19edca3995da.json")) {
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

            Pattern pattern = Pattern.compile("名:\\s*([^\\n/]+)");
            Matcher matcher = pattern.matcher(ocrText);
            String productName = "";
            if (matcher.find()) {
                productName = matcher.group(1);
            }

            return productName;
        }
    }
}
