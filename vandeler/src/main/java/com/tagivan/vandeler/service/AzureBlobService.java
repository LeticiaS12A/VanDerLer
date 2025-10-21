package com.tagivan.vandeler.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Service
public class AzureBlobService {

    private final BlobContainerClient usersContainer;
    private final BlobContainerClient booksContainer;

    public AzureBlobService() {
        String connectionString = System.getenv("AZURE_STORAGE_CONNECTION_STRING");

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        this.usersContainer = serviceClient.getBlobContainerClient("image-users");
        this.booksContainer = serviceClient.getBlobContainerClient("image-books");
    }

    public String uploadFile(MultipartFile file, String type) {
        try {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            BlobContainerClient targetContainer = getContainerByType(type);
            BlobClient blobClient = targetContainer.getBlobClient(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                blobClient.upload(inputStream, file.getSize(), true);
            }

            return blobClient.getBlobUrl(); // URL pública
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo para o Azure Blob.", e);
        }
    }

    public void deleteFile(String fileName, String type) {
        try {
            BlobContainerClient targetContainer = getContainerByType(type);
            BlobClient blobClient = targetContainer.getBlobClient(fileName);
            blobClient.delete();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar arquivo do Azure Blob.", e);
        }
    }

    private BlobContainerClient getContainerByType(String type) {
        if ("users".equalsIgnoreCase(type)) {
            return usersContainer;
        } else if ("books".equalsIgnoreCase(type)) {
            return booksContainer;
        } else {
            throw new IllegalArgumentException("Tipo de container inválido. Use 'users' ou 'books'.");
        }
    }
}