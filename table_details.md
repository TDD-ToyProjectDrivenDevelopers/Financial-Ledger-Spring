# ๐ ํ์ด๋ธ ์ ์์

### ํ์(USER) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|USER_ID|ํ์ ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|ROLE|ํ์ ํ์|USERROLE||Y|
|EMAIL|์ด๋ฉ์ผ|VARCHAR||Y|
|PASSWORD|๋น๋ฐ๋ฒํธ|VARCHAR||Y|
|USERNAME|ํ์ ์ด๋ฆ|VARCHAR||Y|
|IS_DELETED|ํ์ ํํด ์ ๋ฌด|BOOLEAN||N|

### ๊ณ์ข(ACCOUNT) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|ACCOUNT_ID|๊ณ์ข ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|BANK_ID|์ํ ๊ณ ์ ๋ฒํธ|BIGINT|FK|Y|
|USER_ID|ํ์ ๊ณ ์ ๋ฒํธ|BIGINT|FK|Y|
|ACCOUNT_NAME|๊ณ์ข ์ด๋ฆ|VARCHAR||Y|
|DEPOSIT_AMOUNT|์๊ธ ์์ก|BIGINT||Y|

### ์ํ(Bank) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|BANK_ID|์ํ ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|BANK_NAME|์ํ ์ด๋ฆ|VARCHAR||Y|

### ๊ฑฐ๋(TxHistory) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|TX_HISTORY__ID|๊ฑฐ๋ ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|ACCOUNT_ID|๊ณ์ข ๊ณ ์ ๋ฒํธ|BIGINT|FK|Y|
|TX_DATE|๊ฑฐ๋ ๋ ์ง|DATETIME||Y|
|TYPE|๊ฑฐ๋ ์ข๋ฅ|ENUM||Y|
|AMOUNT|๊ฑฐ๋ ๊ธ์ก|BIGINT||Y|

### ์นดํ๊ณ ๋ฆฌ(CATEGORY) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|CATEGORY_ID|์นดํ๊ณ ๋ฆฌ ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|USER_ID|ํ์ ๊ณ ์ ๋ฒํธ|BIGINT|FK|Y|
|CATEGORY_NAME|์นดํ๊ณ ๋ฆฌ ์ด๋ฆ|VARCHAR||Y|

### ์ผ๊ธฐ(DIARY) ํ์ด๋ธ

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|DIARY_ID|์ผ๊ธฐ ๊ณ ์ ๋ฒํธ|BIGINT|PK|Y|
|TX_HISTORY_ID|๊ฑฐ๋ ๊ณ ์ ๋ฒํธ|BIGINT|FK|Y|
|TITLE|์ผ๊ธฐ ์ ๋ชฉ|VARCHAR||Y|
|DESCRIPTION|์ผ๊ธฐ ์์ธ๋ด์ฉ|VARCHAR||N|

### ๊ณตํต ์ปฌ๋ผ(Common Columns)

|์ปฌ๋ผ๋ช|ํ๊ธ๋ช|TYPE|KEY|NOT NULL|
|:---:|:---:|:---:|:---:|:---:|
|CREATED_AT|์์ฑ ๋ ์ง|DATETIME||Y|
|UPDATED_AT|์์  ๋ ์ง|DATETIME||Y|
