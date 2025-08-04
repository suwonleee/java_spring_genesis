package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

// @EqualsAndHashCode를 넣으면 모든 필드를 사용해서 equals(), hashcode()를 생성함
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
