# Trivista 핵심 기능 요약

## 1. 여행 날짜 선택 (캘린더 UI)
- 사용자가 달력에서 여행 날짜 범위를 선택
- 기술 스택: `React.js`, `react-datepicker` 또는 `fullcalendar` 등

## 2. 날씨 API 연동
- 선택한 날짜 범위에 해당하는 지역의 날씨 정보를 외부 API를 통해 조회
- 맑은 날씨(비/눈이 아닌 날씨)만 필터링하여 추천 대상으로 설정
- 사용 API 예시: `OpenWeatherMap`, `WeatherAPI`

## 3. KakaoMap API로 여행지 추천 지역 표시
- 맑은 날씨 지역에 기반하여 KakaoMap에 마커(핀) 표시
- 기술 스택: `KakaoMap JavaScript SDK`
- https://github.com/southkorea/southkorea-maps?tab=readme-ov-file

## 4. 핀 클릭 시 관광정보 목록 AI 추천
- 선택된 위치를 기반으로 다음 정보 목록을 AI가 생성:
    - 관광지
    - 놀거리
    - 먹거리
- 기술 스택: `ChatGPT API` 또는 다른 AI API

## 5. AI 기반 일정 자동 생성
- 추천된 장소들을 기반으로 이동 시간과 위치를 고려한 일정 자동 구성
- 기술 스택:
    - `ChatGPT API` (일정 생성 로직)
    - `KaKao Maps API` (거리/이동 시간 계산)

## 6. 일정 커스터마이징 (수정/삭제/추가)
- 생성된 일정을 사용자가 편집 가능 (추가, 삭제, 순서 변경 등)
- UI 요소: 드래그 앤 드롭 인터페이스 등 (`react-beautiful-dnd` 등 활용 가능)

## 7. PostgreSQL에 일정 저장
- 최종 일정 정보를 DB에 저장
- 기술 스택:
    - `PostgreSQL`
    - 백엔드 프레임워크: `Spring Boot`

## 8. 일정 공유 기능
- 생성된 일정을 링크로 공유 가능 (`/share/:schedule_id`)
- 다른 사용자는 해당 일정을 열람하거나 복사하여 사용할 수 있음
- 기술 스택: UUID 기반 공유 토큰 시스템
