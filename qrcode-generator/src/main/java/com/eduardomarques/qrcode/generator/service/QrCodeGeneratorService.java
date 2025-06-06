package com.eduardomarques.qrcode.generator.service;

import com.eduardomarques.qrcode.generator.dto.QrCodeGeneratorResponse;
import com.eduardomarques.qrcode.generator.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneratorService {

    private final StoragePort storagePort;

    public QrCodeGeneratorService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrCodeGeneratorResponse generateAndUpload(String text) throws WriterException, IOException {

        QRCodeWriter writer =  new QRCodeWriter();

        // Generate the QR code as a BitMatrix
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        // convert the BitMatrix to a PNG image and write it to the output stream
        MatrixToImageWriter.writeToStream(bitMatrix, "png", pngOutputStream);

        byte[] fileData = pngOutputStream.toByteArray();

        // Upload the file to S3 bucket using the StoragePort
        String url = storagePort.uploadFile(fileData, UUID.randomUUID().toString(), "image/png");

        // Return the response with the uploaded URL
        return new QrCodeGeneratorResponse(url);
    }
}
