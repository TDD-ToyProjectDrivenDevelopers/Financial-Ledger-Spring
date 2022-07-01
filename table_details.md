# 📝 테이블 정의서

### 회원(USER) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|USER_ID|회원 고유번호|BIGINT|PK|Y|
|ROLE|회원 타입|USERROLE||Y|
|EMAIL|이메일|VARCHAR||Y|
|PASSWORD|비밀번호|VARCHAR||Y|
|USERNAME|회원 이름|VARCHAR||Y|
|IS_DELETED|회원 탈퇴 유무|BOOLEAN||N|

### 계좌(ACCOUNT) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|ACCOUNT_ID|계좌 고유번호|BIGINT|PK|Y|
|BANK_ID|은행 고유번호|BIGINT|FK|Y|
|USER_ID|회원 고유번호|BIGINT|FK|Y|
|ACCOUNT_NAME|계좌 이름|VARCHAR||Y|
|DEPOSIT_AMOUNT|예금 잔액|BIGINT||Y|

### 은행(Bank) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|BANK_ID|은행 고유번호|BIGINT|PK|Y|
|BANK_NAME|은행 이름|VARCHAR||Y|

### 거래(TxHistory) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|TX_HISTORY__ID|거래 고유번호|BIGINT|PK|Y|
|ACCOUNT_ID|계좌 고유번호|BIGINT|FK|Y|
|TX_DATE|거래 날짜|DATETIME||Y|
|TYPE|거래 종류|ENUM||Y|
|AMOUNT|거래 금액|BIGINT||Y|

### 카테고리(CATEGORY) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|CATEGORY_ID|카테고리 고유번호|BIGINT|PK|Y|
|USER_ID|회원 고유번호|BIGINT|FK|Y|
|CATEGORY_NAME|카테고리 이름|VARCHAR||Y|

### 일기(DIARY) 테이블

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|DIARY_ID|일기 고유번호|BIGINT|PK|Y|
|TX_HISTORY_ID|거래 고유번호|BIGINT|FK|Y|
|TITLE|일기 제목|VARCHAR||Y|
|DESCRIPTION|일기 상세내용|VARCHAR||N|

### 공통 컬럼(Common Columns)

|컬럼명|한글명|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|CREATED_AT|생성 날짜|DATETIME||Y|
|UPDATED_AT|수정 날짜|DATETIME||Y|
