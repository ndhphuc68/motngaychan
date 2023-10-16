package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.UploadDTO;
import com.ndhphuc.motngaythu6.model.Upload;
import com.ndhphuc.motngaythu6.repository.UploadRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadService {

  @Autowired
  UploadRepository uploadRepository;

  public List<Integer> saveFile(List<MultipartFile> listMultipartFile) throws IOException {
    List<Integer> uploadId = new ArrayList<>();
    for (MultipartFile multipartFile : listMultipartFile) {
      Upload upload = new Upload();
      upload.setCreateDate(new Date());
      upload.setType(multipartFile.getContentType());
      upload.setNameImage(multipartFile.getOriginalFilename());
      upload.setImage(multipartFile.getBytes());
      uploadRepository.save(upload);
      uploadId.add(upload.getId());
    }
    return uploadId;
  }

  public List<Integer> saveFileV2(List<UploadDTO> listMultipartFile) throws IOException {
    List<Integer> uploadId = new ArrayList<>();
    for (UploadDTO multipartFile : listMultipartFile) {
      Upload upload = new Upload();
      upload.setCreateDate(new Date());
      upload.setType(multipartFile.getType());
      upload.setNameImage(multipartFile.getName());
      upload.setImage(multipartFile.getUrl().getBytes());
      uploadRepository.save(upload);
      uploadId.add(upload.getId());
    }
    return uploadId;
  }
}
