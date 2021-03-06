package hello.core.lifecycle;

// javax -> 자바진영에서 공식적으로 제공하는 것
// 즉 스프링이 아니고서도 다른 자바관련 프레임워크에서 사용 가능
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    // 연결된 상태에서 서버에 메시지를 던질 수 있다고 가정
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init(){
        // 의존관계 주입이 끝나면
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        // 스프링 컨테이너가 내려가기 전에
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
