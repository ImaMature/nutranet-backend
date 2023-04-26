package com.nutranet.Service;

import com.google.gson.*;
import com.nutranet.Model.DTO.Common.ResponseDTO;
import com.nutranet.Model.DTO.FoodDataDTO;
import com.nutranet.Model.Entity.FoodDataEntity;
import com.nutranet.Model.Repository.FoodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class FoodDataService<T> {
    @Autowired
    FoodDataRepository foodDataRepos;

    @Value("${API_KEY}")
    private String API_KEY;

    public boolean getSavedData() {
        List<FoodDataEntity> rs = foodDataRepos.findAll();
        return !rs.isEmpty();
    }

    public void saveNewData() {
        BufferedReader reader = null;
        try{
            URL url = new URL("https://apis.data.go.kr/1471000/HtfsTrgetInfoService02/getHtfsInfoList02?numOfRows=100&serviceKey="+API_KEY+"&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.connect();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String inputLine = null;

            inputLine = reader.readLine();
            //System.out.println("test : "+inputLine);

            JsonObject jsonObject = (JsonObject) JsonParser.parseString(inputLine);
            //System.out.println(jsonObject);
            double totalCount = Integer.parseInt(String.valueOf(jsonObject.get("body").getAsJsonObject().get("totalCount")));
            //전체 페이지 수 만큼 데이터 요청해야됨.
            // 한번에 100개씩만 데이터 조회 가능
            // 33000개의 데이터가 있다면, 330번 조회해야 함
            // totalCount(전체 결과 수)의 1번째 자리가 0보다 크다면 무조건 올리기

            //totalCount의 1번째 자리수 구하기
            int oneNumber = (int)(totalCount / Math.pow(10, 0)) % 10;
            // System.out.println("ddsd : "+oneNumber);

            //1번째 자리수가 1보다 크면 올리기
            if(oneNumber > 0){
                double mathCeilCount = Math.ceil(totalCount/100);
                System.out.println("mathCeilCount : "+mathCeilCount);
                System.out.println("totalCount : "+totalCount);
                for(int i = 1; i<=mathCeilCount; i++){
                    System.out.println("==> : "+i+"번째 데이터 받아오기");
                    URL url2 = new URL("https://apis.data.go.kr/1471000/HtfsTrgetInfoService02/getHtfsInfoList02?pageNo="+i+"&numOfRows=100&serviceKey="+API_KEY+"&type=json");
                    conn = (HttpURLConnection) url2.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(15000);
                    conn.connect();
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                    while((inputLine = reader.readLine()) != null){
                    JsonObject jsonObject2 = (JsonObject) JsonParser.parseString(inputLine);
                    JsonArray jsonArray = (JsonArray) jsonObject2.get("body").getAsJsonObject().get("items");
                    System.out.println("==> : "+jsonArray);
                        for(int j = 0; j<jsonArray.size(); j++){
                            System.out.println("==> : "+j+"번째 데이터 엔티티로 만들기.");
                            FoodDataEntity foodDataEntity = FoodDataEntity.builder()
                                    .PRDLST_NM(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("PRDLST_NM")).replaceAll("\"", ""))
                                    .PRMS_DT(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("PRMS_DT")).replaceAll("\"", ""))
                                    .DISPOS(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("DISPOS")).replaceAll("\"", ""))
                                    .BSSH_NM(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("BSSH_NM")).replaceAll("\"", ""))
                                    .PRIMARY_FNCLTY(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("PRIMARY_FNCLTY")).replaceAll("\"", ""))
                                    .NTK_MTHD(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("NTK_MTHD")).replaceAll("\"", ""))
                                    .CSTDY_MTHD(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("CSTDY_MTHD")).replaceAll("\"", ""))
                                    .IFTKN_ATNT_MATR_CN(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("IFTKN_ATNT_MATR_CN")).replaceAll("\"", ""))
                                    .STDR_STND(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("STDR_STND")).replaceAll("\"", ""))
                                    .GU_PRDLST_MNF_MANAGE_NO(String.valueOf(jsonArray.get(j).getAsJsonObject().get("item").getAsJsonObject().get("GU_PRDLST_MNF_MANAGE_NO")).replaceAll("\"", ""))
                                    .build();
                            foodDataRepos.save(foodDataEntity);
                        }
                    }
                }
            }else{
                int totalCount2 = (int) Math.ceil(totalCount/100);
                System.out.println("totalCount2 : "+totalCount2);
            }


/*          while((inputLine = reader.readLine()) != null){
                JsonObject jsonObject2 = (JsonObject) JsonParser.parseString(inputLine);
                //JsonArray jsonArray = (JsonArray) jsonObject2.get("body").getAsJsonObject().get("items").getAsJsonObject().get("item");
                //System.out.println(jsonObject2.get("body").getAsJsonObject().get("items"));

            }
*/
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public FoodDataDTO findRecentDatas() {
        Optional<FoodDataEntity> foodDataEntity = foodDataRepos.find10Items();
        return FoodDataDTO.builder()
                .Nno(foodDataEntity.orElseThrow().getNno())
                .PRDLST_NM(foodDataEntity.orElseThrow().getPRDLST_NM())
                .BSSH_NM(foodDataEntity.orElseThrow().getBSSH_NM())
                .build();
    }
}
