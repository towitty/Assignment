# 프로젝트 소개
요구 사항을 구현한 사전 과제로 책 검색과 책을 즐겨찾기 등록할 수 있는 기능이 있습니다.      
요구사항은 아래와 같습니다.

### 요구사항
- **[책 검색, 즐겨찾기]** 2개의 탭으로 구성
   

- **책 검색**
  - 검색할 수 있는 검색창
  - [네이버 책 검색 API 이용](https://developers.naver.com/docs/serviceapi/search/book/book.md#%EC%B1%85)
  - 한번에 10개의 데이터를 불러오며, 한 번 로드할 때 마다 하단에 페이지 번호 표시
  - 더 이상 데이터를 불러올 수 없는 경우 스크롤 멈춤
  - 검색된 책은 image, title, link, 즐겨 찾기 아이콘 표시
  - 즐겨찾기 아이콘 Click 으로 즐겨찾기 추가 및 삭제 가능
    - 즐겨찾기에 추가되어 있는 책은 채워진 아이콘으로 표시
    - 즐겨찾기에 채워진 아이콘 Click 으로 즐겨찾기에서 삭제 및 빈 아이콘으로 표시
  - Click 을 통해 펼쳐 보기 가능
    - 여러 항목이 동시에 펼침 상태를 유지 가능
    - 펼쳐보기 전 title 과 link 는 1줄로 표시하며, 잘린 내용은 ... 으로 생략 표시
    - 펼쳐보기 후 title 과 link 모든 텍스트를 볼 수 있음
    - 펼쳐진 상태에서 link 를 클릭하면 웹 브라우저를 통해 해당 링크로 이동
   

- **즐겨 찾기**
  - 즐겨 찾기에 추가된 책 목록을 표시
  - 즐겨 찾기 목록은 앱이 종료되도 영속적으로 보관
  - 아이콘 Click 으로 즐겨찾기에서 삭제
  
# Screen
![Image](https://github.com/user-attachments/assets/df7aa42c-ecad-46fa-a9c8-988e68c9ad13)

# Infomation
**Language** : Kotlin    
**Libs** : Coroutine, Hilt, Room, Retrofit, Glide, Paging   
**Architecture** : MVVM, Android clean architecture    
**Build** : Gradle, version catalog   
**SDK** : min 24. target 34   
**Version** : semantic versioning 1.0.0   
**IDE** : Android Studio Koala | 2024.1.2

# Comment
###### feature
- 단순한 UI 시나리오로 Navigation Component를 사용하지 않았고, Tab이 추가될 수 있다는 확장 가능성을 생각해 구현했습니다.
- 작은 규모의 프로젝트로 multi module 을 적용 하지 않고, 구현하는 동안 별도의 branch 를 생성하지 않았습니다.
- 요구 사항 중 `한번 로드할 때 마다 하단에 페이지 번호 표시` 를 구현 하는데 로드 횟수가 아닌 `한번에 로드하는 아이템의 개수`를 기준으로 페이지 번호를 표시했습니다.
이는 요구 사항과 다소 차이가 있어 보여 고민되는 부분입니다.

###### Issue
- 검색된 책의 title, link 가 짧아 확장할 필요가 없음에도, link 클릭 시 바로 웹 브라우저로 이동하지 못하는 문제가 있습니다.
- 앱 재시작 시 즐겨찾기로 추가된 책이 새로운 검색 결과에 동기화 되지 않는 문제가 있습니다.

###### Improvement
- 처음 설계할 때 Navigation 을 사용하지 않았지만, 더 복잡한 UI 시나리오로 확장 할 수 있도록 적용하면 좋을 것 같습니다.
- 검색 결과가 없거나, 데이터를 불러오는 상태를 사용자에게 표시하여 UX를 개선하면 좋을 것 같습니다.
- 네트워크 환경이 원활하지 않을 때 사용자에게 표시하여 UX를 개선하면 좋을 것 같습니다.
- 탭 이동 후 검색 탭으로 돌아왔을 때, 이전 로드된 페이지를 유지하지 않고 최초 페이지로 이동하여 보여지고 있습니다. 이전 로드된 페이지가 보여지도록 개선하면 좋을 것 같습니다.