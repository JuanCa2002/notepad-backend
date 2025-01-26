package com.ensolvers.mynotepad.my_notepad.repository;

import com.ensolvers.mynotepad.my_notepad.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM CategoryEntity c WHERE c.name = :name " +
            "AND c.user.id = :userId")
    boolean existsByName(@Param("name") String name, @Param("userId") BigInteger userId);

    @Query("SELECT c FROM CategoryEntity c WHERE c.user.id = :userId")
    List<CategoryEntity> findAllByUser(@Param("userId")BigInteger userId);

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :id AND c.user.id = :userId")
    Optional<CategoryEntity> findByIdAndUser(@Param("id") Integer id, @Param("userId") BigInteger userId);

    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN TRUE ELSE FALSE END FROM NoteEntity n WHERE n.category.id = :categoryId")
    boolean existsByCategory(@Param("categoryId") Integer categoryId);
}
