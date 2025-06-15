package com.cybernet.cybernetserver.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "atr1")
    private String atr1;
    @Column(name = "atr2")
    private String atr2;
    @Column(name = "atr3")
    private String atr3;
    @Column(name = "atr4")
    private String atr4;
    @Column(name = "atr5")
    private String atr5;
    @Column(name = "atr6")
    private String atr6;
    @Column(name = "atr7")
    private String atr7;
    @Column(name = "atr8")
    private String atr8;
    @Column(name = "atr9")
    private String atr9;
    @Column(name = "atr10")
    private String atr10;
    @Column(name = "atr_name_1")
    private String atrName1;
    @Column(name = "atr_name_2")
    private String atrName2;
    @Column(name = "atr_name_3")
    private String atrName3;
    @Column(name = "atr_name_4")
    private String atrName4;
    @Column(name = "atr_name_5")
    private String atrName5;
    @Column(name = "atr_name_6")
    private String atrName6;
    @Column(name = "atr_name_7")
    private String atrName7;
    @Column(name = "atr_name_8")
    private String atrName8;
    @Column(name = "atr_name_9")
    private String atrName9;
    @Column(name = "atr_name_10")
    private String atrName10;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

}
