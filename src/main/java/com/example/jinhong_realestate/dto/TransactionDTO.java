package com.example.jinhong_realestate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionDTO {
    private Long transId;
    private String houseType;
    private String bldgNm;
    private String sggCd;
    private String sggNm;
}
