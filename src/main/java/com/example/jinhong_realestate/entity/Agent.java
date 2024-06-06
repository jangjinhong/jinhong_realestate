package com.example.jinhong_realestate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Agent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "AGENT_ID")
    private Long agent_id;
    @Column(name = "BJDONG_CD")
    private String bjdongCd;
    @Column(name = "BJDONG_NM")
    private String bjdongNm;
    @Column(name = "ROAD_CD")
    private String roadCd; //도로명 코드
    @Column(name = "RDEALER_CD")
    private String rdealerCd;   //중개업 등록 번호
    @Column(name = "RDEALER_NM")
    private String rdealerNm;   //대표자명
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "SGG_NM")
    private String sggNm;
    @Column(name = "SGG_CD")
    private String sggCd;
    @Column(name = "CMP_NM")
    private String cmpNm;
    @Column(name = "STS_GBN")
    private String statusGbn; //상태구분

    @JsonIgnore
    @OneToMany(mappedBy = "agent", fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "agent", fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;
}