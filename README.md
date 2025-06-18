# 🐐 GoatSaeng (갓생)  

---

## 📱 소개

**GoatSaeng(갓생)**은 사용자의 **루틴 관리**, **음성 기반 기능**, **뉴스 확인**, 그리고 **IoT 기반 기능(NFC/Beacon)**을 통해 ‘갓생’을 살아갈 수 있도록 돕는 Android 자기관리 앱입니다.

---

## 🚀 주요 기능

### ✅ 필수 기능
- **To-do 리스트**: 간단한 할 일 관리 및 메모 기능
- **TTS (Text-to-Speech)**: 입력한 텍스트를 음성으로 출력
- **STT (Speech-to-Text)**: 음성을 텍스트로 변환
- **알람 기능**: 시간 기반 알림 설정 및 푸시 알림
- **RSS 뉴스 뷰어**: 카테고리별 최신 뉴스 제공

### 🔧 추가 구현 기능
- **NFC 운세 태그**: 태그를 스캔하면 오늘의 운세를 표시
- **Beacon 기반 자리 찾기**: 사용자의 위치와 비콘 거리 표시
- **커스텀 앱 아이콘**: 페이커를 닮은 인물이 염소를 드는 갓생 아이콘
- **직관적인 UX**: iOS 스타일의 심플한 UI 구성

---

## 🛠 기술 스택

| 기술 | 내용 |
|------|------|
| **언어** | Java |
| **플랫폼** | Android (minSdk 26, targetSdk 34) |
| **데이터 저장** | SQLite |
| **음성 처리** | Android TTS, STT API |
| **뉴스 제공** | Naver/MK RSS feed 파싱 |
| **IoT 연동** | NFC (NDEF), Beacon (Bluetooth LE) |
| **UI 스타일** | Material Design + iOS 스타일 커스터마이징 |
| **버전 관리** | GitHub |

---

## 📸 앱 아이콘

<p align="center">
  <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" width="150"/>
</p>

---

## 📂 프로젝트 구조 요약

