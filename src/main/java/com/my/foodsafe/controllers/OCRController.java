package com.my.foodsafe.controllers;

import com.my.foodsafe.services.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OCRController {
    @Autowired
    private OCRService ocrService;
    @PostMapping("/ocr")
    @ResponseBody
    public ResponseEntity<String> processOCR(MultipartFile image) {
        try {
            String result = ocrService.processOCR(image);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to process OCR: " + e.getMessage());
        }
    }
}