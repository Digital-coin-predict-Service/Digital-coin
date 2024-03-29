# 가상 화폐 예측 사이트
## LSTM 모델을 활용하여 예측된 가상화폐의 가격을 보여주는 사이트입니다.
#### ✔ **LSTM 모델의 전처리와 학습 및 결과는 BTC-Prediction의 readme에 있습니다.**
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/5d7589ef-f299-42f8-b110-9f3fec56bb4c)
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/bdaf0062-de4b-4498-a65d-2e0b537d94f4)
### 제작 동기
최근 들어 흔히 개인 투자자들은 이전에 비해 늘어났습니다. 

가상화폐에 투자하는 개인 투자자들 중 가상화폐를 잘 모르는 사람들은 **짧은 시간 안에 거래량, 가격 등을 보고 삽니다.**

하지만 가격이 내릴 것이라는 예측이 있다면, 오를 것이라는 예측이 있다면, 사람들은 더이상 가상화폐만 바라보며 살지 않아도 되지 않을까요?

저의 프로젝트는 이러한 의문점에서 출발하였습니다.

#### 왜 다른 주식도 많은데 굳이 가상화폐냐?

가상화폐는 다른 주식에 비해 사람들이 오직 차트에 더 의존해서 투자하는 경향이 있습니다.

차트의 특정한 패턴을 읽어서, 적절한 시기에 매수, 매도하는 것입니다.

이러한 **패턴을 굳이 사람이 읽지 않고 인공지능이 읽어서 예측**할 수 있을 것입니다.
***********************
## 기능
#### 1. 비트코인 가격 예측 제공
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/c90ae85d-9e0d-42f6-8e7b-dd770603cd74)
자신이 원하는 가상화폐 종목을 선택하면 해당 종목의 가격 예측이 그래프로 나타납니다.

가상화폐 목록에 존재하는 원은 이전 시간 예측 시에 1분뒤의 값과, 현재의 값의 차이입니다.

백분율은 해당 값의 차이가 전체에서 얼마만큼의 오차인치 오차율을 보여줍니다.

#### 2. 유저 차별화
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/25385ad4-66e1-469b-8434-41c6cd7e62ec)
쿠키에 세션 id, 사용자 정보를 담겨 서버가 받게 되면 유효한 세션과 사용자인지 판단합니다.

유효하지 않은 유저, 즉 로그인을 하지않은 유저라면, **예측된 10개의 값 중 5개만 열람**할 수 있습니다.

5분 뒤의 10분 이동 평균 추세를 열람할 수 있는 것입니다.

**로그인을 하면 예측된 10개의 값 모두 열람**할 수 있습니다.

![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/224c04a8-c535-4863-8cec-40089ddb3190)
로그인을 하지 않으면 즐겨찾기 기능을 사용할 수 없습니다.

#### 3. 즐겨찾기
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/030659ba-7277-472d-b5df-b15f49c6ec08)

사용자가 특정 가상화폐를 즐겨찾기로 등록을 해둔다면, 해당 종목이 사용자가 찾기 쉽도록 위로 올라옵니다. (새로고침 해야함)

유저가 루트 주소에 들어왔을 때, 해당 유저의 이름으로 된 즐겨찾기 목록이 있는지 DB에서 조회합니다.

**즐겨찾기 목록이 존재한다면, 전체 종목 리스트에서 즐겨찾기 목록을 위로 올립니다.**

#### 4. 상승률 하강률 제공
![image](https://github.com/Digital-coin-predict-Service/Digital-coin/assets/112631585/bf0b44cd-09df-472a-874d-592827cc578b)

사용자가 선택한 종목에 대해서 예측된 추세라면 몇 % 상승 또는 하강할지를 백분율로 나타냅니다.

그래프로 시각적인 확인도 좋지만, **숫자로 더 정확한 수치를 얻는다면 투자에 확신을 얻을 수 있을 것입니다.**
*********************
## 추가 요구 사항
#### 1. LSTM 모델의 다양한 시간 예측
현재는 1분마다의 예측을 보여주지만, 앞으로 5분 간격, 10분, 30분, 1시간, 일 간격의 가격 예측을 제공할 예정입니다.

#### 2. LSTM 모델의 다양한 output
현재는 LSTM이 단 하나의 예측값만을 내놓습니다. 이 예측 하나가 잘못되면 모델의 신뢰성은 급격히 떨어집니다.

따라서 LSTM 모델을 수정하여 LSTM 모델이 여러개의 output을 내놓아서 이를 히트맵으로 그려주고 각 영역의 확률을 나타내주면 사용자에게 더 좋은 매도 / 매수 타이밍을 제공할 수 있습니다.

#### 3. 업비트와 계정 연동 혹은 사용자의 매수 가격 입력
업비트 api를 이용하여 사용자 정보를 얻어오거나, 사용자가 직접 본인의 매수 가격을 입력하면 앞으로 추세라면 몇 % 이득을 볼 수 있는지 계산하여 사용자에게 보여줍니다.
