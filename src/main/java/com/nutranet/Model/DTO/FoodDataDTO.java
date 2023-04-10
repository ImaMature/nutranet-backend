package com.nutranet.Model.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDataDTO {
    private int Nno;                    //제품 번호
    private int pageNo;                 //페이지 번호
    private int totalCount;             //전체 결과 수

    private String PRDLST_NM;           //제품명
    private String PRMS_DT;             //등록일자
    private String DISPOS;              //성상
    private String BSSH_NM;             //회사명
    private String PRIMARY_FNCLTY;      //기능성내용
    private String NTK_MTHD;            //섭취량/섭취방법
    private String CSTDY_MTHD;          //소비 및 유통기준
    private String IFTKN_ATNT_MATR_CN;  //섭취시 주의사항
    private String STDR_STND;           //기준 및 규격
    private String GU_PRDLST_MNF_MANAGE_NO; //품목제조관리번호
}
