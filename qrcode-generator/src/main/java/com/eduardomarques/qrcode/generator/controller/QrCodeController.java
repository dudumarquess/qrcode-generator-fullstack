package com.eduardomarques.qrcode.generator.controller;

import com.eduardomarques.qrcode.generator.dto.QrCodeGeneratorRequest;
import com.eduardomarques.qrcode.generator.dto.QrCodeGeneratorResponse;
import com.eduardomarques.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGeneratorResponse> generate(@RequestBody QrCodeGeneratorRequest request) {
        try {
            QrCodeGeneratorResponse response = qrCodeGeneratorService.generateAndUpload(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
