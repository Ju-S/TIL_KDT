# *인증시스템*
Base Url: `/api/auth`

---
### 기능

1. [로그인](#로그인)
1. [회원가입](#회원가입)
1. [oauth 로그인](#oauth-로그인)
1. [oauth 회원가입](#oauth-회원가입)
1. [사용자 정보 수정](#사용자-정보-수정)
---

# 로그인
## Description
사용자 아이디와 비밀번호를 통해 로그인.

## Request
**POST** `/api/auth/login` <br>
Content-Type: `application/json`

```json
{
    "userId": "example-userId",
    "password": "example-password"
}
```
## Response
```json
{
  "token": "example-JWT-token",
  "user": {
    "id": 1,
    "name": "example-name"
  }
}
```
---
# 회원가입
## Description
사용자 아이디, 비밀번호, 닉네임, 프로필 사진(구현미정)을 받아 회원가입.

## Request
**POST** `/api/auth/register` <br>
Content-Type: `application/json`

```json
{
    "userId": "exampleUserID",
    "password": "examplePassword",
    "name": "exampleName"
}
```
## Response
```json
{
  "token": "exampleJWTToken",
  "user": {
    "id": 1,
    "username": "exampleName"
  }
}
```
---
# oauth
## Description
소셜 로그인 기능. <br>
사용자가 provider(카카오, 네이버, 구글 등)에 인증을 한 뒤, 발급받은 인가 코드를 전달. <br>
서버에서 인가 코드를 provider서버로 보내 액세스 토큰을 받아 사용자 정보를 조회하여 응답.

## Request
**POST** `/api/oauth/login` <br>
Content-Type: `application/json`

```json
{
  "provider": "kakao",
  "code": "authorization-code-from-provider"
}
```
## Response
```json
{
  "token": "example-JWT-token",
  "user": {
    "id": 1,
    "name": "example-name",
    "providerId": "example-provider-id"
  }
}
```
---
# 사용자 정보 수정
## Description
사용자 이름 또는 프로필 사진(구현미정)을 받아 기존 정보 업데이트.

**POST** `/api/v1/auth/register` <br>
Content-Type: `application/json`

## Request
```json
{
    "name": "modified-name"
}
```
## Response
```json
{
  "HttpStatus": "200: success, 400: error"
}
```
---