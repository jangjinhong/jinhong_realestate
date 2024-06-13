package com.example.jinhong_realestate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "transactions")
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANS_ID")
    private Long trans_id;
    @Column(name = "BJDONG_CD")
    private String bjdongCd;
    @Column(name = "LAND_GBN_NM")
    private String landGbnNm;
    @Column(name = "HOUSE_TYPE")
    private String houseType;
    @Column(name = "BLDG_NM")
    private String bldgNm; //건물명
    @Column(name = "ACC_YEAR")
    private int accYear;
    @Column(name = "SGG_NM")
    private String sggNm;
    @Column(name = "OBJ_AMT")
    private int objAmt;
    private String floor;
    @Column(name = "BUILD_YEAR")
    private String build_year;
    @Column(name = "REQ_GBN")
    private String reg_gbn;  //중개거래 or 직거래
    @Column(name = "RDEALER_LAWDNM")
    private String rdealer_lawd_nm; //신고한 개업공인중개사 시군구명


    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
}