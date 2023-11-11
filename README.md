### RabbitMq Example

### 시나리오

서버를 시작할 때
100만건의 데이터를 저장소에 저장한 뒤, 100만건의 정기결제를 시도한다.
이 때 메시지 큐를 이용하여
만건 씩 데이터를 잘라서, 메시지에 담아서
결제를 하도록 처리한다. 

결제 프로세스는 아래와 같다. 
- 유저의 빌링키 유무을 확인한다. 
- 유저가 빌링키가 있을 경우 PG사에 결제를 요청한다.
- 결제 결과를 우리 저장소에 저장한다. 이 때 성공/실패 유무를 따진다.


### 테스트

- 1000000 건의 데이터는 기본 설정으로 한번에 보낼 수 있다.


