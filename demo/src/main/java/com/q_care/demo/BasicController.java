package com.q_care.demo;

import com.q_care.demo.patient.PatientsForm;
import com.q_care.demo.patient.PatientsRepository;
import com.q_care.demo.patient.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor

public class BasicController {

    private final PatientsRepository patientsRepository;
    private final PatientsService patientsService;

    @GetMapping("/about")
    public String about() {
        return "about.html";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    // 언어 선택 페이지
    @GetMapping("/main")
    public String home(){
        return "main.html";
    }

    @GetMapping("/language")
    public String language(){
        return "language.html";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String join(@RequestParam(value = "lang", required = true) String language, Model model){
        model.addAttribute("lang", language);
        return "join.html";
    }

    @PostMapping("/request-join")
    @ResponseBody
    public ResponseEntity<?> requestJoin(@RequestParam PatientsForm patientsForm) {
        // 사용자가 입력한 정보가 알맞은 형식이면 회원가입 완료
        try {
            // 비밀번호 일치 확인
            if (!patientsForm.getPassword().equals(patientsForm.getConfirmPassword())) {
                return ResponseEntity.badRequest().body(Map.of("errorMessage", "비밀번호가 일치하지 않습니다."));
            }

            // 회원가입 처리 (DB 저장 등)
            patientsService.savePatient(patientsForm);

            return ResponseEntity.ok().build();  // 성공 시 200 OK 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("errorMessage", "등록 중 오류 발생"));
        }
    }
}
