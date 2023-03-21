- 약국 찾기 서비스

  1. 약국 현황 데이터(공공 데이터)를 관리하고 있다 라고 가정하고, 약국 현황 데이터는 위도 경도의 위치 정보 데이터를 가지고 있다.
  
  2. 해당 서비스로 주소 정보를 입력하여 요청하면 위치 기준에서 가까운 약국 3곳을 추출한다.
  
  3. 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다.
     - 정확한 주소를 입력 받기 위해 kakao 우편번호서비스 사용
     
  4. 주소는 정확한 상세 주소를 제외한 주소 정보를 이용하여 추천한다. ex) 서울 성북구 종암로 10길
  
  5. 입력 받은 주소를 위도, 경도로 변환하여 기존 약국 데이터와 비교 및 가까운 약국을 찾는다.
     - 지구는 평면이 아니기 때문에 구면에서 두 점 사이의 최단 거리 구하는 공식이 필요함.
     - 두 위 경도 좌표 사이의 거리를 haversine formula로 계산
     - 지구가 완전한 구형이 아니므로 아주 조금의 오차가 있다.
     
  6. 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 약국만 추천한다.
  
  7. 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공한다.


- Tech Stack
  - JDK11
  - Spring Boot 2.6.7
  - Spring Data JPA
  - Gradle
  - Handlebars
  - Lombok
  - Github
  - Docker
  - AWS EC2
  - Redis
  - MariaDB
  - Spock
  - Testcontainers

- Feature List
  - Spring Data JPA를 이용한 CRUD 메서드 구현
  - Spock를 이용한 테스트 코드 작성
  - Testcontainers를 이용하여 독립 테스트 환경 구축
  - 카카오 주소검색 API 연동하여 주소를 위도, 경도로 변환
  - 추천 결과를 카카오 지도 URL로 연동하여 제공
  - 공공 데이터를 활용하여 개발하기 (약국 현황 데이터)
  - 도커를 사용하여 다중 컨테이너 애플리케이션 만들기
  - 애플리케이션을 클라우드 서비스에 배포
  - Spring retry를 이용한 재처리 구현(카카오 API의 네트워크 오류 등에 대한 재처리)
  - redis를 이용하여 성능 최적화
