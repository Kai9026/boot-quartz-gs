package com.github.kai9026.quartzgs.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "campaign")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;
}
