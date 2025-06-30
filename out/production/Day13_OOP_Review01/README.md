프로젝트는 MVC 패턴을 적용한다.
데이터 저장소는 ArrayList를 사용한다.(Generic 필수)
i값이 필요 없는 for문은 foreach로 대체한다.

1.  ID, Title 과 Genre를 멤버필드로 가지는 Movie 클래스를 생성한다.
    Setter / Getter / Constructor / Default Constructor 제작
    Optional. 영화 출시일 - publish_date 필드 추가

2. 프로그램이 시작되면 다음과 같은 메뉴를 띄운다.
    ```
   << Netflix 영화 관리 시스템 >>
   1. 신규 영화 등록
   2. 영화 목록 출력
   3. 영화 검색(제목 or 장르)
   4. 영화 수정(ID)
   5. 영화 삭제(ID)
   0. 시스템 종료
   ```

3. 신규 영화 등록 시
ID는 1001 부터 1씩 자동 증가값을 사용(입력 X)
title, genre만 입력 // Optional 선택 시 출시일까지 입력(yy/MM/dd)

4. 영화 목록 출력 시
```
ID   Title   Genre   (pub_date)
1001 아바타   판타지   2024.03
```