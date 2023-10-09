package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Integer> {

  @Query(value = "select * from uploads where id = :id", nativeQuery = true)
  Upload findByUploadId(Integer id);
}
