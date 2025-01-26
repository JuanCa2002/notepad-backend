package com.ensolvers.mynotepad.my_notepad.entity;

import com.ensolvers.mynotepad.my_notepad.config.converters.LocalTimeStringConverter;
import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_NOTE", sequenceName = "SEQ_NOTE", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTE")
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 80 ,nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Convert(converter = LocalTimeStringConverter.class)
    @Column(name = "creation_time", nullable = false, length = 8)
    private LocalTime creationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 8, nullable = false)
    private NoteState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
