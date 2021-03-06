# π Gubun - κ°κ³λΆ

## π₯ μ¬μ©μ μΈν°νμ΄μ€(UI)

- μΆκ° μμ 

## βοΈ κ°λ° νκ²½(Development Environment)

| λΆλ₯ | κ°λ°νκ²½ | 
|---|---|
| μ΄μμ²΄μ  | Windows 10 64bit / Mac OS |
| κ°λ°λκ΅¬ | Intellij IDEA, Gradle |
| νλ μμν¬ | Spring Boot 2.7.0 |
| λ°μ΄ν°λ² μ΄μ€ | PostgreSQL (Release 13.7) |
| λ²μ  κ΄λ¦¬ | Github, Git |
| λ°°ν¬ λ° μ΄μ | AWS EC2, AWS RDS, AWS S3, Docker, Github Actions  |
| μ€νμμ€ λ° μΈλΆ λΌμ΄λΈλ¬λ¦¬ | Naver API, Kakao API |


## π  μΈλΆ κΈ°μ  μ€ν(Tech Stack)

### λ°±μλ(Back-end)
- **Java 11**
- **Spring Boot 2.7.0**
	- Spring Web MVC
	- Spring Data JPA
	- Spring Security

### λ°μ΄ν°λ² μ΄μ€(Database)

- **PostgreSQL (Release 13.7)**

### νλ‘ νΈμλ(Front-end)

- **Thymeleaf**
- **Bootstrap**

### ETC

- μΆκ° μμ 

## π λλ©μΈ λͺ¨λΈ λΆμ(Domain Model Analysis)

### νμ(User)

- νμκ³Ό κ³μ’μ κ΄κ³ : νμμ μ¬λ¬ κ°μ κ³μ’λ₯Ό μμ ν  μ μλ€.(1:N)
- νμκ³Ό μΉ΄νκ³ λ¦¬μ κ΄κ³ : νμμ μ¬λ¬ κ°μ μΉ΄νκ³ λ¦¬λ₯Ό λ³΄μ ν  μ μλ€.(1:N)

### κ³μ’(Account)

- κ³μ’μ νμμ κ΄κ³ : νμμ μ¬λ¬ κ°μ κ³μ’λ₯Ό μμ ν  μ μλ€.(N:1)
- κ³μ’μ κ±°λμ κ΄κ³ : ν κ°μ κ³μ’μμ μ¬λ¬λ²μ κ±°λκ° λ°μν  μ μλ€.(1:N)
- κ³μ’μ μνμ κ΄κ³ : ν κ°μ κ³μ’λ νλμ μνμ λμλλ€. (1:1)

### μν(Bank)

- κ³μ’μ μνμ κ΄κ³ : ν κ°μ κ³μ’λ νλμ μνμ λμλλ€. (1:1)

### μΉ΄νκ³ λ¦¬(Category)

- μΉ΄νκ³ λ¦¬μ νμμ κ΄κ³ : νμμ μ¬λ¬ κ°μ μΉ΄νκ³ λ¦¬λ₯Ό λ³΄μ ν  μ μλ€.(N:1)
- μΉ΄νκ³ λ¦¬μ κ±°λμ κ΄κ³ : ν κ°μ μΉ΄νκ³ λ¦¬λ μ¬λ¬ κ°μ κ±°λμμ μκΈΈ μ μλ€. λν ν κ°μ κ±°λλ μ¬λ¬ κ°μ μΉ΄νκ³ λ¦¬κ° μ‘΄μ¬ν  μ μλ€. (N:N)

### κ±°λ

- κ±°λμ κ³μ’μ κ΄κ³ : ν κ°μ κ³μ’μμ μ¬λ¬λ²μ κ±°λκ° λ°μν  μ μλ€.(N:1)
- κ±°λμ μΉ΄νκ³ λ¦¬μ κ΄κ³ : ν κ°μ μΉ΄νκ³ λ¦¬λ μ¬λ¬ κ°μ κ±°λμμ μκΈΈ μ μλ€. λν ν κ°μ κ±°λλ μ¬λ¬ κ°μ μΉ΄νκ³ λ¦¬κ° μ‘΄μ¬ν  μ μλ€. (N:N)
- κ±°λμ μΌκΈ°μ κ΄κ³ : ν κ°μ κ±°λμμλ ν λ²μ μΌκΈ°λ₯Ό μμ±ν  μ μλ€.(1:1)

### μΌκΈ°(Diary)

- μΌκΈ°μ κ±°λμ κ΄κ³ : ν κ°μ κ±°λμμλ ν λ²μ μΌκΈ°λ₯Ό μμ±ν  μ μλ€.(1:1)

## π νμ΄λΈ μ μμ(Entity Details)

- [λ°λ‘κ°κΈ°](./table_details.md)

## π μν°ν°-κ΄κ³ λͺ¨λΈ(Entity Relationship Diagram)

![Entity_Details](./images/entity_details.png)

## π νΈλ¬λΈ μν(Trouble Shooting)

- μΆκ° μμ 