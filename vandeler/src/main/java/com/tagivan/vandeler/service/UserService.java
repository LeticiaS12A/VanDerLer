package com.tagivan.vandeler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tagivan.vandeler.dto.UserDTO;
import com.tagivan.vandeler.entity.User;
import com.tagivan.vandeler.entity.UserRoles;
import com.tagivan.vandeler.exception.DuplicateUserException;
import com.tagivan.vandeler.exception.NoResultsException;
import com.tagivan.vandeler.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AzureBlobService azureBlobService;

    /** ===============================
     *  LIST USERS
     *  =============================== */
    public UserDTO list(Long id) {
        return new UserDTO(repository.findById(id)
                .orElseThrow(() -> new NoResultsException("Usuário não encontrado.")));
    }

    public List<UserDTO> listAll() {
        List<User> users = repository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    /** ===============================
     *  CREATE USER
     *  =============================== */
    public UserDTO save(UserDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateUserException("Já existe um usuário cadastrado com este e-mail.");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(UserRoles.User);
        user.setProfileUrl(dto.getProfileUrl());

        User saved = repository.save(user);
        return new UserDTO(saved);
    }

    /** ===============================
     *  UPDATE (Partial)
     *  =============================== */
    public UserDTO update(UserDTO dto, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoResultsException("Usuário não encontrado para atualização."));

        if (dto.getName() != null && !dto.getName().equals(user.getName())) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(dto.getPassword()));
        }
        if (dto.getRoles() != null) {
            user.setRoles(dto.getRoles());
        }
        if (dto.getProfileUrl() != null) {
            user.setProfileUrl(dto.getProfileUrl());
        }

        User updated = repository.save(user);
        return new UserDTO(updated);
    }

    /** ===============================
     *  UPDATE PROFILE PHOTO
     *  =============================== */
    public UserDTO updatePhoto(Long id, MultipartFile file) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoResultsException("Usuário não encontrado."));

        if (file != null && !file.isEmpty()) {
            // Always uploads to the image-users container
            String url = azureBlobService.uploadFile(file, "users");
            user.setProfileUrl(url);
        }

        User updated = repository.save(user);
        return new UserDTO(updated);
    }

    /** ===============================
     *  DELETE USER
     *  =============================== */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoResultsException("Usuário não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }
}