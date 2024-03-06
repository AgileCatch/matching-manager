![header](https://capsule-render.vercel.app/api?type=cylinder&color=0:EAEDFE,100:D6FDE9&height=230&section=header&text=matching%20manager&fontColor=000000&fontSize=70&animation=fadeIn&fontAlignY=50&desc=MVVM%20Architecture&descAlignY=70)

# LINK_UP - MATCHING-MANAGER

⚽️ **프로젝트 소개**

"Matching-Manager" 는 스포츠 경기 매칭, 경기장 추천, 용병 모집을 도와주는 스포츠 매칭 플랫폼입니다.

</br>

💡 **이런 사람들에게 추천합니다**

```

1. 경기장 추천 : 경기장을 예약할때 근처에 있는 경기장 정보를 알고싶은 사람!

- 지역별, 종목별 필터를 통해 경기장 정보를 제공해 줍니다.

2. 용병 모집 : 경기는 하고싶지만 함께 할 인원이 부족한 사람들!

- 인원이 부족한 사람들은 용병 모집/신청 이 가능해 팀에 가입하거나 인원을 보충 할수 있습니다.

3. 경기 매칭 - 운동을 하고싶지만, 함께 경기 할 상대팀이 없을때!

- 지역, 운동 종목을 나눠 함께 경기를 진행할 상대와 매칭을 도와줍니다.

```

---

## 🎲 **기간 및 기여도**

**기간** : 23.10.10 ~ 23.11.17 `6주`

**참여 인원** : `4명`

**기여도 : `40%`**

---

## ⚙️ **내가 사용한 기술 스택**
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=white"/> <img src="https://img.shields.io/badge/AndroidStudio-3DDC84?style=flat-square&logo=AndroidStudio&logoColor=white"/>
<img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/notion-000000?style=flat-square&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/slack-4A154B?style=flat-square&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=flat-square&logo=figma&logoColor=white">

**Architecture** - MVVM (Model-View-ViewModel)

**Jetpack** - ViewModel, SharedViewModel, LiveData, LifeCycle, ViewBinding, AAC

**UI Frameworks** - Fragment, ViewPager2, ListAdapter, Bottom Sheet Dialog

---

## **🏟️ 주요 역할**
- **MVVM 디자인 패턴 적용**하여 **유지보수성**향상
- **ViewModel과 SheardViewmodel**을 사용해 데이터를 관리
    - 각 Fragment간 UI와 데이터를 분리하기 위해 활용
    - LiveData로 데이터를 관찰하여 데이터가 변경될때마다 UI를 업데이트할 수 있도록 관리
- **ViewPager2 를 적용**하여, 4개의 Fragment를 함께 작업할 수 있도록 main구상
    - 각 Fragment와 title을 data class로 저장하여 분리해줌
    - 코드의 간소화와 가독성 향상
- **ListAdapter를 도입**하여 코드 간소화
    - LiveData와 DiffUtil을 활용해 변경된 부분만 업데이트하고 쉽게 데이터를 관찰할 수 있도록 함
- **UX 향상**을 위해 Toolbar, Spinner, BottomSheetDialog 등과 같은 공통적으로 사용되는 요소들 분리, 관리하여 일관성과 재사용성을 높임
- **사용자 피드백을 반영**하여 Filter 기능을 추가해 사용자들이 쉽게 데이터를 필터링할 수 있도록 함
- Figma를 사용해 **디자이너와 협업** 하여 앱의 전반적인 UX/UI 를 담당

---

## **🏀구현 페이지 및 과정 설명**

### **1) mainPage**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/12d29e37-f76d-4624-a157-20c6f9f7c1d6" width="500" />


- **ViewPager2 x Tablayout** 으로 main화면 틀 구성
- Fragment 간 **Toolbar**를 적용하여 리팩토링 함으로써 UI일관성과 유지보수성을 높임
  
---

### **2) Arena**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/6ea0c30b-75ab-4f0e-820f-091088efe89c" width="1000" />


- **경기장 검색 필터기능**, 지역별로 필터를 적용하여 사용자가 더욱 쉽게 데이터를 필터링 할 수 있도록 함

---

### **3) Match**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/b10eb46a-a1e1-462d-9010-8bc3a02c10bb" width="500" />


- **경기 매칭 필터**, 함께 경기할 상태팀을 구하기 위한 경기매칭 페이지 종목별, 지역별 필터를 적용

---

### **4) Team**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/dcb35de0-de38-4f89-88d6-142ce6a30c05" width="1000" />


- **용병 모집/신청 필터 적용**
- **경기 종목, 지역 필터 적용**
### ViewType 적용으로 RecyclerView 재사용성 증가

- **기존:** 서로 다른 타입의 **신청/모집**의 데이터를 각각의 `ListAdapter` 로 한 화면에 출력
    - 유지보수의 측면에서 복잡성 증가
    - 재사용성 및 이해도 감소

- **ViewType 적용후** 문제를 해결하기 위해 **ViewType**을 나눠 **하나의 ListAdapter**로 관리
    - 특성에 따라 EnumClass로 진입 타입을 분리
    - 별도의 `ViewHolder` 로 데이터 구분
    - **재사용성**, **가독성** ,**유지보수성이** 크게 향상
    
    [💡 [Devlog] EnumClass로 타입을 분리하여 프래그먼트간 데이터 전달하기](https://agilecatch.github.io/devlog/fixit/2023-10-18-%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%EA%B0%84-%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%A0%84%EC%86%A1/)
    
---

### Filter & Spinner를 Util로 분리하여 코드 재사용성 증가

- **기존 :** 여러종류의 `Spinner` 를 5개의 화면에서 똑같이 사용하다보니 **중복된 코드**로 인한 유지보수성이 떨어짐 을 느끼게됨

- **Util로 분리 후**  **Spinners Util**로 분리하여 **중복된 코드를 제거**하고 코드 **재사용성을 높임**
    - **code line 400줄 감소**, 유지보수성이 향상됨
    - Util을 사용하여 스피너의 공통적인 기능을 구현함으로써, 코드의 **가독성**과 **일관성**이 향상됨
    [](https://agilecatch.github.io/devlog/kotlin/2023-10-14-Spinner%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0/)[💡[Devlog] 필터 적용후 리사이클러뷰 생성시 중간부터 생기는 문제 해결 방법](https://agilecatch.github.io/devlog/fixit/2023-11-03-%EB%A6%AC%EC%82%AC%EC%9D%B4%ED%81%B4%EB%9F%AC%EB%B7%B0-%EC%9E%90%EB%8F%99%EC%8A%A4%ED%81%AC%EB%A1%A4/)

---

### **5) Detail/Add (MATCH/TEAM)**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/a00528ae-cae7-4586-92af-631b01da2b79" width="500" />


- 게시글의 **상세정보**를 확인할 수 있는 디테일 페이지 구현
- 게시글을 추가할 수 있는 Add Page 구현
### Sealed Class 사용으로 코드 안정성을 높임

- **기존 :** 중복된 데이터가 많은 신청과 모집의 **두가지 모델을 함께 묶어**서 구현할 일이 생김.
    - `Enum`을 사용했을 때는 각 모델에 대한 **별도의 클래스를 구현**해야해서 코드의 라인 수가 많아지고, 유지보수성이 떨어지게됨

- **Sealed Class를 선택한 후** 각 모델을 하나의 클래스로 구현하여 **공통된 데이터를 함께 관리**할 수 있게됨
    - **코드의 라인 수가 50줄 감소, 유지보수성 향상**
    - 이로 인해, 코드의 가독성, 안정성이 향상

---

### MVVM 디자인패턴 적용

- **코드를 모델, 뷰, 뷰모델로 분리**하여 **각각의 책임을 명확히 정의**
    - 이러한 개선 사항을 통해, **코드의 분리와 유지보수 향상**, 테스트 용이성 향상 등의 효과를 얻을 수 있었음

---

### ListAdapter 도입으로 갱신 데이터 시간의 단축과 유지보수성을 높임

- **기존 :** `RecyclerView Adapter`를 사용하던 중 **데이터를 여러번 갱신**해주는 코드에 불편함을 느낌
- 추가, 삭제, 수정 등의 데이터 관리 로직 모두 구현
- 데이터의 유지보수 측면에서의 아쉬움
- **ListAdapter 도입 후** 복잡한 데이터 관리 로직을 작성할 필요 사라져 **코드 간소화**, 가독성의 다양한 이점을 얻게됨
    - `DiffUtil`을 활용해 변경된 부분만 업데이트
    - 복잡한 데이터 관리 로직을 작성할 필요가 없어짐
    - `LiveData`와 함께 사용하면서 **데이터의 관찰**이 용이해짐

---

### ViewPagerAdapter와 TabLayout를 사용해 코드를 간소화

- **기존 :** 여러 개의 Activity를 사용하면 화면을 전환 할때 각 Activity마다 액티비티 객체와 프래그먼트 객체가 생성되어 **코드 복잡성 증가**
- **ViewPager와 TabLayout 사용 후** 각 화면을 프래그먼트로 구현
    - **5개의 Activity 객체의 수를 1개로** 줄임
    - 각 fragment와 title을 data class로 저장하여 갯수대로 정의해 화면에 꽂아줌으로써, **코드의 간소화**와 **가독성** 향상

[💡[Devlog] 프래그먼트에 ViewPager2 연결하는 방법 및 스크롤 중첩 문제 해결하기](https://agilecatch.github.io/devlog/fixit/2023-10-12-Fragment%EC%95%88%EC%97%90-Viewpager2%EB%84%A3%EA%B8%B0/)

---

### Figma를 사용해 디자이너와 협업 하여 앱의 전반적인 UX/UI 를 담당

- Figma를 사용해 디자이너와 협업 하여 앱의 전반적인 UX/UI 를 담당

[+더많은 프로젝트 보러가기[Devlog] AgileCatch's 기술블로그](https://agilecatch.github.io)


