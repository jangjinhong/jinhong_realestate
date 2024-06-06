package com.example.jinhong_realestate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class OpenApiController {

    @GetMapping("/api")
    public ResponseEntity<String> callApi(
            @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNo,
            @RequestParam(value="perPage", required=false, defaultValue="10") String perPage,
            @RequestParam(value="serviceKey") String serviceKey
    ) {
        try {
            // 서비스 키를 URL 인코딩
            String encodedServiceKey = URLEncoder.encode(serviceKey, "UTF-8");

            // API 호출을 위한 URL 생성
            String apiUrl = "https://api.odcloud.kr/api/RealEstateTradingSvc/v1/getRealEstateTradingArea";
            String requestUrl = apiUrl + "?page=" + pageNo + "&perPage=" + perPage + "&serviceKey=" + encodedServiceKey;

            // API 호출
            URL url = new URL(requestUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();

            // 응답 코드 확인
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // API 응답 데이터 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // JSON 또는 XML 응답 반환
                return ResponseEntity.ok(response.toString());
            } else {
                // API 호출 실패 시 응답코드 반환
                return ResponseEntity.status(responseCode).body("API 호출 실패: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }
}
