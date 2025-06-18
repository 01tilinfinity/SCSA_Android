# 🐐 GoatSaeng - 갓생 관리 어플

> "페이커처럼 살고 싶다."  
하루를 잘 살고 싶은 당신을 위한 셀프 매니지먼트 앱, **GoatSaeng**!

---

## 🧠 개요

**GoatSaeng**은 하루하루 '갓생'을 살고 싶은 현대인들을 위해 개발된 **자기관리 통합 앱**입니다.  
알람, 할 일, 뉴스 피드, 음성 인식/합성, NFC·Beacon 기반 기능을 통합해, 하나의 앱으로 다양한 자기관리 루틴을 실현할 수 있도록 합니다.

---

## 🛠️ 사용 기술 (Tech Stack)

| 영역 | 스택 |
|------|------|
| 🧱 플랫폼 | Android SDK, Java |
| 🎨 UI | XML 레이아웃, ConstraintLayout |
| 🔊 음성 | Android SpeechRecognizer, TextToSpeech |
| 📡 근거리 통신 | NFC, BLE Beacon |
| 📰 뉴스 피드 | RSS (Naver, 매일경제 등) |
| 🧪 기타 | CountDownTimer, AlarmManager, BroadcastReceiver 등 Android 컴포넌트 활용

---

## ✨ 주요 기능

| 기능명 | 설명 |
|--------|------|
| ⏰ **알람 설정** | 알람 생성, 수정, 삭제 가능. `AlarmManager` 및 `BroadcastReceiver`를 활용. |
| 🗣️ **음성 인식 & 합성** | STT로 음성 입력 → 텍스트 변환, TTS로 텍스트 → 음성 출력 기능 지원. |
| ✅ **할 일 관리** | SQLite 기반의 To-Do 관리 기능. CRUD 완비. |
| 📰 **뉴스 뷰어** | 정치/경제/사회/세계 등 카테고리별 최신 RSS 뉴스 조회. |
| 📱 **NFC 운세 보기** | NFC 태그를 읽어 당일 운세를 랜덤으로 표시. |
| 📍 **Beacon 기반 자리찾기** | 가장 가까운 Beacon과의 거리 측정 후 표시. (BLE 활용) |

---

## 📱 앱 아이콘

<p align="center">
  <img src="https://github.com/01tilinfinity/SCSA_Android/blob/main/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="app icon" width="150"/>
</p>

> 페이커를 닮은 캐릭터가 염소를 들어올리는 일러스트에 '갓생' 텍스트가 강조된 아이콘

---

## 📂 프로젝트 구조 요약

```bash
GoatSaeng/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/scsa/goatsaeng/   # Java 소스코드
│   │   │   ├── res/
│   │   │   │   ├── layout/                # 화면 UI 구성 XML
│   │   │   │   ├── drawable/              # 이미지 리소스
│   │   │   │   ├── mipmap-*               # 앱 아이콘 리소스
│   │   │   │   ├── values/                # colors, strings 설정
│   │   │   │   └── xml/                   # 알람 관련 설정 XML
│   │   └── AndroidManifest.xml           # 앱 구성 및 권한 설정
├── gradle/                                # Gradle Wrapper
├── build.gradle                           # 빌드 설정
└── README.md                              # 문서화
