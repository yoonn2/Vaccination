package com.example.Vaccination.controller;

import com.example.Vaccination.dto.CustomerDto;
import com.example.Vaccination.dto.RecordDto;
import com.example.Vaccination.dto.VaccineDto;
import com.example.Vaccination.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MainService mainService;
    // 도시 코드와 도시 이름을 매핑하는 map
    private static final Map<String, String> cityMap = new HashMap<>();
    static {
        cityMap.put("10", "서울");
        cityMap.put("20", "경기");
        cityMap.put("30", "강원");
        cityMap.put("40", "대구");
    }
    // 홈페이지로 이동
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    // 고객 조회 페이지로 이동
    @GetMapping("/customers")
    public String getCustomers(Model model) {
        List<CustomerDto> customers = mainService.getAllCustomers();
        for (CustomerDto customer : customers) {
            // p_bday를 localdate로 변환
            LocalDate birthDate = LocalDate.parse(customer.getP_bday(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            // localdate를 형식화된 문자열로 변환
            String formattedBirthDate = birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
            customer.setFormattedBday(formattedBirthDate); // 형식화된 생년월일 설정
            customer.setAge(2020 - birthDate.getYear()); // 2020년 기준으로 나이 계산
            customer.setP_city(cityMap.get(customer.getP_city())); // p_city를 변환된 도시 이름으로 설정
        }
        model.addAttribute("customers", customers); // 변환된 고객 목록을 모델에 추가
        return "customers";
    }

    // 예방접종등록 페이지로 이동
    @GetMapping("/order")
    public String signupPage(Model model) {
        // 필요한 경우 다음 번호를 생성하여 모델에 추가
        String nextNo = "";
        model.addAttribute("nextno", nextNo);
        return "order"; // signup.html 뷰로 반환
    }
    // 예방접종등록 처리
    @PostMapping("/regist")
    public String registVaccine(
            // 요청 파라미터를 메서드 파라미터로 바인딩한다.
            // html폼에서 전달된 값이 각 변수에 저장한다.
            @RequestParam("p_seno") String pSeno,
            @RequestParam("p_no") String pNo,
            @RequestParam("i_code") String iCode,
            @RequestParam("p_date") String pDate,
            Model model) { // Spring mvc에서 사용하는 model 객체로, 뷰에 데이터를 전달하는 역할

        // 새로운 vaccineDto 객체를 생성하고, 폼에서 전달된 값을 설정한다.
        // 예방접종 정보를 DTO에 담기
        VaccineDto vaccination = new VaccineDto();
        vaccination.setP_seno(pSeno); // 예방접종이력번호 설정
        vaccination.setP_no(pNo);// 고객번호 설정
        vaccination.setI_code(iCode);// 백신코드 설정
        vaccination.setP_date(pDate);// 접종일자 설정

        // 서비스 레이어를 통해 예방접종 정보를 저장
        mainService.registVaccination(vaccination);
        return "index";
    }

    @GetMapping("/record")
    public String recordPage(Model model) {
        List<RecordDto> records = mainService.getAllRecords();
        model.addAttribute("list", records);
        return "record";
    }

    @GetMapping("/injection")
    public String getVaccines(Model model) {
        mainService.getVaccines(model);
        return "injection";
    }
}
