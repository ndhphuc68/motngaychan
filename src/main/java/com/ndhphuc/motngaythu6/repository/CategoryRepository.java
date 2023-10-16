package com.ndhphuc.motngaythu6.repository;

import com.ndhphuc.motngaythu6.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

  @Query(value = "select * from categorys where id = :id",nativeQuery = true)
  Category findCategoryById(Integer id);

  @Query(value = "select * from categorys where parent_category_id is null",nativeQuery = true)
  List<Category> findCategoryParentCategoryIdNull();

  @Query(value = "select * from categorys where parent_category_id = :id",nativeQuery = true)
  List<Category> findCategoryByParentCategoryId(Integer id);
}
