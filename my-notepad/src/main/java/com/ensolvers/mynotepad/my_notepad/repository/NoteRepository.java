package com.ensolvers.mynotepad.my_notepad.repository;

import com.ensolvers.mynotepad.my_notepad.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    @Query("SELECT n FROM NoteEntity n WHERE n.user.id = :userId AND (:categoryId IS NULL OR n.category.id = :categoryId) ")
    List<NoteEntity> findAllByUser(@Param("userId") BigInteger userId, @Param("categoryId") Integer categoryId);

}
