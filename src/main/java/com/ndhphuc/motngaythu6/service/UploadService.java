package com.ndhphuc.motngaythu6.service;

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
import java.util.Date;

@Service
public class UploadService {

  @Autowired
  UploadRepository uploadRepository;

  public boolean saveFile(MultipartFile multipartFile) throws IOException {
    Upload upload = new Upload();
    upload.setCreateDate(new Date());
    upload.setType(multipartFile.getContentType());
    upload.setNameImage(multipartFile.getOriginalFilename());
    upload.setImage(multipartFile.getBytes());
    uploadRepository.save(upload);
    return true;
  }
}
