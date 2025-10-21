package com.tagivan.vandeler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tagivan.vandeler.service.AzureBlobService;

@RestController
@RequestMapping("/arquivos")
public class FileController {

    private final AzureBlobService azureBlobService;

    public FileController(AzureBlobService azureBlobService) {
        this.azureBlobService = azureBlobService;
    }

    // Upload file to specified container (users or books)
    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type) {

        String url = azureBlobService.uploadFile(file, type);
        return ResponseEntity.ok("Arquivo enviado com sucesso! URL: " + url);
    }

    // Delete file from specified container (users or books)
    @DeleteMapping("/delete/{type}/{fileName}")
    public ResponseEntity<String> delete(
            @PathVariable String type,
            @PathVariable String fileName) {

        azureBlobService.deleteFile(fileName, type);
        return ResponseEntity.ok("Arquivo deletado com sucesso!");
    }
}