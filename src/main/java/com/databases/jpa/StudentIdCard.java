package com.databases.jpa;

import jakarta.persistence.*;

@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")
        }
)
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_card_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;

    @Column(
            name = "card_number",
            nullable = false,
            length = 15
    )
    private String card_number;


    public StudentIdCard() {
    }

    public StudentIdCard(String card_number) {
        this.card_number = card_number;
    }

    public long getId() {
        return id;
    }

    public String getCard_number() {
        return card_number;
    }
}