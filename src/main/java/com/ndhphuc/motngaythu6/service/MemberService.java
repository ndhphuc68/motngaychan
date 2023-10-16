package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.CreateMemberDTO;
import com.ndhphuc.motngaythu6.model.Role;
import com.ndhphuc.motngaythu6.model.User;
import com.ndhphuc.motngaythu6.repository.RoleRepository;
import com.ndhphuc.motngaythu6.repository.UserRepository;
import com.ndhphuc.motngaythu6.utils.ActionUser;
import com.ndhphuc.motngaythu6.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MemberService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  RoleRepository roleRepository;

  public boolean createMember(CreateMemberDTO memberDTO) throws Exception {
    User user = userRepository.findByUsername(memberDTO.getUsername());
    if (user == null) {
      User userCreate = new User();
      userCreate.setName(memberDTO.getName());
      userCreate.setUsername(memberDTO.getUsername());
      userCreate.setPassword(encoder.encode(memberDTO.getPassword()));
      userCreate.setEmail(memberDTO.getEmail());
      userCreate.setCreateDate(new Date());
      userCreate.setPhone(memberDTO.getPhone());
      Set<Role> roles = new HashSet<>();
      Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
      userCreate.setRoles(roles);
      userRepository.save(userCreate);
      return true;
    } else {
      throw new Exception("Username đã tồn tại");
    }
  }

  public List<CreateMemberDTO> getListMember(String username, Integer isBlock, String textSearch) {
    List<Integer> listBlock = new ArrayList<>();
    if (isBlock == null) {
      listBlock.add(1);
      listBlock.add(0);
    } else {
      listBlock.add(isBlock);
    }
    String search = "%%";
    if (textSearch != null) {
      search = "%" + textSearch + "%";
    }
    List<User> listUser = userRepository.getListUserByRoles(RoleEnum.ROLE_USER.toString(), username, listBlock, search);
    if (listUser != null) {
      List<CreateMemberDTO> listMember = new ArrayList<>();
      CreateMemberDTO createMemberDTO = null;
      for (User user : listUser) {
        createMemberDTO = new CreateMemberDTO();
        createMemberDTO.setUsername(user.getUsername());
        createMemberDTO.setName(user.getName());
        createMemberDTO.setPhone(user.getPhone());
        createMemberDTO.setEmail(user.getEmail());
        createMemberDTO.setCreateDate(user.getCreateDate());
        createMemberDTO.setIsBlock(user.getIsBlock());
        listMember.add(createMemberDTO);
      }
      return listMember;
    }
    return null;
  }

  public boolean actionMember(String username, String type) {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      if (ActionUser.DELETE.getAction().equals(type)) {
        userRepository.delete(user);
        return true;
      } else if (ActionUser.BLOCK.getAction().equals(type)) {
        if (user.getIsBlock() == 1) {
          user.setIsBlock(0);
        } else {
          user.setIsBlock(1);
        }
        userRepository.save(user);
        return true;
      }
    }
    return false;
  }
}
