![header](https://capsule-render.vercel.app/api?type=cylinder&color=0:EAEDFE,100:D6FDE9&height=230&section=header&text=matching%20manager&fontColor=000000&fontSize=70&animation=fadeIn&fontAlignY=50&desc=MVVM%20Architecture&descAlignY=70)

# LINK_UP - MATCHING-MANAGER

⚽️ **프로젝트 소개**

```

"Matching-Manager" 는 스포츠 경기 매칭, 경기장 추천, 용병 모집을 도와주는 스포츠 매칭 플랫폼입니다.

```

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

</br>

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

## 🎲 **기간 및 기여도**

**기간** : 23.10.10 ~ 23.11.17 `6주`

**참여 인원** : `4명`

**기여도 : `40%`**

---

# **🏟️ 주요 역할 & 본인이 구현한 페이지**
</br>
- MVVM 아키텍처 적용 ,양방향 DataBinding을 사용하여 UI 업데이트와 사용자 입력 처리를 간편하게 만듬
</br>
- 역할 분담을 위해 여러개의 액티비티를 사용하지않고 4개의 fragnet를 함께 작업할 수 있도록 main구상(ViewPager2 적용),각 fragment와 title을 data class로 저장하여 갯수대로 정의해 화면에 꽂아줌으로써, 코드의 간소화와 가독성 향상
</br>
- RecyclerView Adapter를 사용하던 중 데이터를 여러번 갱신해주는 코드에 불편함을 느껴 ListAdapter를 도입하여 코드 간소화, LiveData와 DiffUtil을 활용해 변경된 부분만 업데이트하고 쉽게 데이터를 관찰할 수 있도록 함
</br>
- UX 향상을 위해 Toolbar, Spinner, BottomSheetDialog 등과 같은 공통적으로 사용되는 요소들 분리, 관리하여 일관성과 재사용성을 높임
</br>
- 사용자 피드백을 반영하여 Filter 기능을 추가해 사용자들이 쉽게 데이터를 필터링할 수 있도록 하였음
</br>
- 각 Fragment간 UI와 데이터를 분리하기 위해 ViewModel과 SheardViewmodel을 사용해 데이터를 관리하였고, LiveData로 데이터를 관찰하여 데이터가 변경될때마다 UI를 업데이트할 수 있도록 관리해줌
</br>
- Figma를 사용해 디자이너와 협업 하여 앱의 전반적인 UX/UI 를 담당


---

### **1) mainPage**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/12d29e37-f76d-4624-a157-20c6f9f7c1d6" style="zoom:50%;" />


- **ViewPager2 x Tablayout** 으로 main화면 틀 구성
- Fragment 간 **Toolbar**를 적용하여 리팩토링 함으로써 UI일관성과 유지보수성을 높임
  
---

### **2) Arena**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/6ea0c30b-75ab-4f0e-820f-091088efe89c" style="zoom:50%;" />


- **경기장 검색 필터기능**, 지역별로 필터를 적용하여 사용자가 더욱 쉽게 데이터를 필터링 할 수 있도록 함

---

### **3) Match**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/b10eb46a-a1e1-462d-9010-8bc3a02c10bb" style="zoom:50%;" />


- **경기 매칭 필터**, 함께 경기할 상태팀을 구하기 위한 경기매칭 페이지 종목별, 지역별 필터를 적용

---

### **4) Team**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/dcb35de0-de38-4f89-88d6-142ce6a30c05" style="zoom:50%;" />


- **용병 모집/신청 필터 적용**
- **경기 종목, 지역 필터 적용**
- 중복된 코드의 Filter & Spinner를 Util로 분리 ➡ 중복된 코드를 제거하여 코드 400줄 감소, 일관성과 재사용성을 높임
- 서로 다른 타입의 신청/모집의 데이터를 각각의 ListAdapter 로 한 화면에 출력하여 유지보수측면에서 복잡성증가, 이 문제를 해결하기 위해 ViewType을 나눠 하나의 ListAdapter로 관리할 수 있도록 EnumClass로 진입 타입을 분리하고 별도의 ViewHolder 로 데이터 구분 ➡ 재사용성, 가독성 ,유지보수성이 크게 향상

---

### **5) Detail/Add (MATCH/TEAM)**

<img src="https://github.com/matching-manager/matching-manager/assets/106515742/a00528ae-cae7-4586-92af-631b01da2b79" style="zoom:50%;" />


- 게시글의 **상세정보**를 확인할 수 있는 디테일 페이지 구현
- 게시글을 추가할 수 있는 Add Page 구현
- 작성된 글의 내용이 각 페이지와 글 내용에 맞게 데이터를 관리할 수 있도록 sealed Class로 리팩토링하여 공통된 데이터를 함께 관리할 수 있도록함 ➡ 유지보수성 향상, 코드의 가독성, 안정성이 향상


