# 나의 습관 및 하루를 기록하는 앱

## 서비스 소개
운동, 반려동물 육아, 사소한 습관 등 사용자의 다양한 습관과 하루를 기록하는 앱의 백엔드 서비스 소스코드 및 설정을 관리합니다.

## 기술 스택
- Java 17  
- Spring Boot 3.5.3
- Spring Security + JWT 
- Spring Data JPA  
- MySQL  
- Firebase Admin SDK 
- Swagger 

## 환경 설정
- MySQL 8 이상 (Docker 컨테이너 포함)  
- Firebase 서비스 계정 키(`src/main/resources/firebase-service-account.json`)  

## 실행 방법
1. 프로젝트 클론 및 빌드  
2. `.env` 파일 생성 및 환경변수 설정  
3. `docker-compose up -d` 명령어로 컨테이너 실행  

## API 문서
- API 문서는 현재 내부 개발용으로만 운영 중입니다.
- 정식 배포 시 공개 URL을 별도 안내할 예정입니다.
