package com.q_care.demo;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReservationMail(String to, String name, Long hospitalId, LocalDate date, LocalTime time) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("(큐-케어)예약 완료 안내");
            helper.setText(
                    "<h2>예약 완료</h2><p>안녕하세요 " + name + "고객님 , " + hospitalId + "번 병원 예약이 " +date+time+ "에 정상적으로 완료되었습니다. " +
                            "(해당 메일을 창업 경진대회 로컬히어로 만들기 프로젝트 발표자료를 목적으로 전송되었습니다. 실제로 예약된 것은 아니며 무시하셔도 괜찮습니다.)" +
                            "-로컬국토대장정 6조-</p>",
                    true // true = HTML 사용
            );

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("메일 전송 실패", e);
        }
    }
}
