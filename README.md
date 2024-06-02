# 순천향대학교 학내순환버스 도착/출발 정보 서비스
순천향대학교 학내순환버스의 정류장별 도착 및 출발 정보를 사용자에게 제공해주는 서비스이다.

## 기능 동작 시나리오
1. 각 정류장에 설치된 하드웨어가 서버로 가공된 데이터를 전송한다.
2. 서버에서는 하드웨어에서 보낸 데이터를 검증한 후, DB에 저장한다.

## 서비스 동작 시나리오
1. 사용자가 서버에 페이지를 요청한다
2. 서버에서는 요청한 페이지를 DB의 정보와 함께 전달한다.
3. 사용자는 요청한 페이지 정보를 통해 본인이 얻고자한 정보를 확인할 수 있다.

## 백엔드 파트 기술 스택
### Server/DB
- Java 17
- Spring boot 3
- Spring data JPA
- MariaDB
- Thymeleaf
- Gradle
- Junit5
- Jacoco

### Monitoring
- Prometheus
- Grafana
- Grafana Alert

### INFRA
- Nginx
- Docker
- Docker-compose
- AWS EC2
- AWS RDS
- AWS Route53
- AWS VPC

### 스택 정리
<img src="Backend Stack.png">

## 백엔드 서비스 아키텍처
<img src="Backend Architecture.png">

## 서버 기능
### 하드웨어 담당 서버
- 수신 데이터 검증 기능
- 검증 통과한 수신 데이터 DB 저장 기능

### 서비스 담당 서버
- 클라이언트 요청 페이지 전달 기능
- 클라이언트 요청 환경에 따른 UI 분류 기능 (모바일 사용자와 PC/패드 접속 유저 구분)

## 모니터링 기능
- 각 담당 서버별 metric을 수집하는 DB역할의 프로메테우스 설치
- 시간은 1m 단위로 지정하여, 너무 많은 부하는 주지 않도록 설정
- 각 프로메테우스의 정보를 한눈에 보기 편하게 하기 위해 그라파나 대시보드 사용
- 그라파나 알림을 통해 심각한 상황 혹은 그러한 상황이 초래될 것으로 예측되는 상황을 이메일 알림을 통해 확인할수 있도록 함

## 배포 과정
- 도커를 통해 각 서버를 별도의 환경으로 관리한다
- 도커 컴포즈를 통해 각 컨테이너 관리를 용이하게 하고 같은 네트워크로 묶어서 각 컨테이너간 통신도 이루어질 수 있도록 구성
- 서버의 구성은 웹서버 역할을 해줄 NGINX와 하드웨어와 서비를 담당하는 각각의 스프링 부트 WAS서버로 구성
- 서버와 모니터링 환경은 AWS EC2에 배포, 데이터베이스는 AWS RDS에 배포
- AWS VPC로 지정된 포트인 80번 포트로만 서버 EC2에 접속하도록 인바운드 규칙 설정
- AWS Route53으로 미리 가비아에서 구매해둔 도메인명과 매칭시켜서 해당 도메인 명으로 접속 가능하도록 설정

## 최종 배포 서버
- 도메인명: http://www.sch-busstation.store
- 도착/출발 정보 페이지: http://www.sch-busstation.store/sch/station/page
- 시간표 페이지: http://www.sch-busstation.store/sch/station/timetable


## 기타: 
- 세부 기능과 최적화 과정 등에 관한 내용은 개인 블로그에 업로드할 예정
  개인 블로그: https://velog.io/@hwangjeyeon/posts
- 하드웨어 기능은 설명 생략

