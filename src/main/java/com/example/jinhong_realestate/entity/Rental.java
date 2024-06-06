package com.example.jinhong_realestate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REN_ID")
    private Long ren_id;
    @Column(name = "BJDONG_CD")
    private String bjdongCd;
    @Column(name = "LAND_GBN_NM")
    private String landGbnNm;
    @Column(name = "BLDG_NM")
    private String bldgNm; //건물명
    @Column(name = "ACC_YEAR")
    private int acc_year;
    @Column(name = "CNTRCT_DE")
    private LocalDate cntrctDe;
//    @Column(name = "SGG_CD")
//    private String sggCd;
    @Column(name = "SGG_NM")
    private String sggNm;
    @Column(name = "RENT_FEE")
    private Long rentFee;
    @Column(name = "RENT_GTN")
    private Long rentGtn;   // 월/전세
    @Column(name = "HOUSE_GBN_NM")
    private String houseGbnNm;
    private String floor;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
}
