package com.recordmanagement.habitlog.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * FirebaseConfig - Firebase Admin SDK 초기화 설정 클래스
 *
 * Firebase 서비스 계정 키 JSON 파일을 이용해 FirebaseApp 인스턴스를 생성한다.
 * 애플리케이션 시작 시 한 번만 초기화되며, 이후 Firebase 기능(푸시 알림, 인증 등)을 사용할 수 있도록 준비한다.
 *
 * @author 전우선
 * @since 2025-07-21
 */
@Configuration
public class FirebaseConfig {

    /**
     * 애플리케이션 시작 후 자동 실행되는 초기화 메서드
     *
     * classpath 내에 위치한 서비스 계정 키 JSON 파일을 로드하여
     * FirebaseOptions 객체를 생성하고 FirebaseApp을 초기화한다.
     *
     * @throws IOException JSON 파일 로드 실패 시 예외 발생
     */
    @PostConstruct
    public void initialize() throws IOException {
        // 서비스 계정 키 JSON 파일을 클래스패스에서 읽어옴
        InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("firebase-service-account.json");

        // Firebase 옵션 설정: 인증 자격증명 포함
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // FirebaseApp이 아직 초기화되지 않은 경우에만 초기화 수행
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}