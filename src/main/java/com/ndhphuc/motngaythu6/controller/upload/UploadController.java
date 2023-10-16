package com.ndhphuc.motngaythu6.controller.upload;


import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.UploadDTO;
import com.ndhphuc.motngaythu6.model.Upload;
import com.ndhphuc.motngaythu6.repository.UploadRepository;
import com.ndhphuc.motngaythu6.service.UploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Upload Controller")
@RestController
@RequestMapping(value = "/api/v1/upload")
@CrossOrigin(origins = "*")
public class UploadController {

  @Autowired
  UploadService uploadService;

  @Autowired
  UploadRepository uploadRepository;

  @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ApiResponse uploadImage(@RequestParam("file") List<MultipartFile> file) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(uploadService.saveFile(file));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }

  @PostMapping(value = "/imageV2")
  public ApiResponse uploadImageV2(@RequestBody List<UploadDTO> uploadDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(uploadService.saveFileV2(uploadDTO));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setMessage(e.getMessage());
      apiResponse.setSuccess(false);
    }
    return apiResponse;
  }

  @GetMapping(path = {"/image/{id}"})
  public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) throws IOException {

    Upload upload = uploadRepository.findByUploadId(id);

    return ResponseEntity
            .ok()
            .contentType(MediaType.valueOf(upload.getType()))
            .body(upload.getImage());
  }
}
