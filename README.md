# housingFinanceService
주택 금융 서비스 API 개발

## 개발환경

- Backend
  - JAVA8
  - Spring Boot 1.5.9
  - JPA
  - Maven
  - MariaDB

## 빌드 및 실행

```
 # IntelliJ Using
  - Reimport maven
  - Run application 

 # active Profile 
  - local
    
 # MariaDB 설치 후 계정 정보 설정
  - application-local.properties
```
## 기본 제약사항

```
- ORM 사용
- 주택금융 공급기관은 독립 엔티티로 디자인
- 단위 테스트 코드 작성
- JSON 형태의 입출력

* 추가 제약사항
- JWT(Json Web ToKen) 이용 Token 기반 API 인증 기능 추가
```

## 문제해결 전략

### 1. 1. 데이터 파일 파싱

```
1. OpenCSV를 이용하여 파일 읽기
2. 오픈 API를 호출하면 대부분 JSON으로 데이터를 받기 때문에 특정 위치에 있다고 가정하여 파일을 읽어오기로 결정
3. Resource 폴더 하단에 csv 파일 위치
4. 값이 없을 경우엔 빈 값으로 파싱되지 않도록 처리
```

### 1. 2. JPA
```
1. JPA 개념 습득 필요
2. Spring Data JPA 사용하기로 결정
3. 집합 함수가 필요한 쿼리는 JPQL을 이용
4. 중복 코드를 최대한 제거
5. 어느정도 정제된 데이터를 DB에서 가져온 후 서비스에서 매핑
```


## 2. API 정보

### 2. 1. 기본정보

- 데이터 타입 : JSON 

| 구분    | 내용            | 비고                             |
| :------ | :---------------| :-------------------------------|
| code    | 응답코드        | 200, 201, 204, 500 등            |
| data    | 요청에 대한 내용 |                                 |

### 2. 2. API LIST

#### 2.2.1. 데이터 생성

> URL : /api/rowData/
>
> Method : POST
>


#### 2.2.2. 주택 금융 공급 기관(은행) 목록 조회

> URL : /api/banks/findAll
>
> Method : GET
>


#### 2.2.3. 년도별 각 금융기관 지원 금액 합계 조회

> URL : /api/bankApply/sum
>
> Method : GET
>


#### 2.2.4. 지원 금액이 가장 큰 기관 조회

> URL : /api/bankApply/max
>
> Method : GET


#### 2.2.5. 외환은행 지원 금액중 평균이 가장 작은 금액과 큰 금액 조회

> URL : /api/bankApply/keb
>
> Method : GET

