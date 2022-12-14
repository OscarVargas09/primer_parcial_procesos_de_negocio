package com.primer_parcial8.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6, nullable = false, unique = true)
    private String codProd;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    private Date dateRegister;

    @Column(length = 1000, nullable = false)
    private int stock;

    @Column(nullable = false)
    private double priceSale;

    @Column(nullable = false)
    private double priceBuy;

    @ManyToOne
    private Category  category;

    @ManyToOne
    private User user;
}
