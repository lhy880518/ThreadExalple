# ThreadExalple

- Java는 다중 상속을 지원하지 않습니다. 따라서 Thread 클래스를 상속하게 되면 하위 클래스는 다른 클래스는 상속할 수가 없습니다. 
  Runnable 인터페이스를 구현하는 클래스는 다른 클래스를 상속받을 수 있습니다.

- Thread 클래스의 모든 것을 상속받는 것이 너무 부담되는 경우에는 Runnable을 구현하는 편이 나을지도 모릅니다.
