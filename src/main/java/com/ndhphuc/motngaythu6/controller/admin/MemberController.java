package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.dto.ApiResponse;
import com.ndhphuc.motngaythu6.dto.CreateMemberDTO;
import com.ndhphuc.motngaythu6.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/admin/member")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class MemberController {

  @Autowired
  MemberService memberService;

  @PostMapping("/create")
  public ApiResponse createMember(@RequestBody CreateMemberDTO memberDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (memberDTO == null) {
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Data null");
        return apiResponse;
      }
      apiResponse.setSuccess(memberService.createMember(memberDTO));
      apiResponse.setMessage("Create Member Success");
    } catch (Exception e) {
      apiResponse.setSuccess(false);
      apiResponse.setMessage(e.getMessage());
    }
    return apiResponse;
  }

  @PostMapping("/update")
  public ApiResponse updateMember(@RequestBody CreateMemberDTO memberDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (memberDTO == null) {
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Data null");
        return apiResponse;
      }
      apiResponse.setData(memberService.updateMember(memberDTO));
      apiResponse.setMessage("Update Member Success");
    } catch (Exception e) {
      apiResponse.setSuccess(false);
      apiResponse.setMessage(e.getMessage());
    }
    return apiResponse;
  }

  @GetMapping(value = "/list-member")
  public ApiResponse getListMember(@RequestParam(required = false) String textSearch, @RequestParam(required = false) String username, @RequestParam(required = false) Integer isBlock) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      apiResponse.setData(memberService.getListMember(username, isBlock,textSearch));
      apiResponse.setSuccess(true);
    } catch (Exception e) {
      apiResponse.setSuccess(false);
      apiResponse.setMessage(e.getMessage());
    }
    return apiResponse;
  }

  @GetMapping(value = "/action")
  public ApiResponse actionMember(@RequestParam String username, @RequestParam String type) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (!StringUtils.hasText(username) || !StringUtils.hasText(type)) {
        apiResponse.setSuccess(false);
      }
      apiResponse.setSuccess(memberService.actionMember(username, type));
      apiResponse.setMessage("Success");
    } catch (Exception e) {
      apiResponse.setSuccess(false);
      apiResponse.setMessage(e.getMessage());
    }
    return apiResponse;
  }
}
