package graduation.busstation.hardware.enums;


/**
 * ARRIVED: 도착
 * DEPARTED: 출발
 * TBD: (TO BE DETERMINED)의 약자로 미확정을 의미함. 즉 도착정보없음을 대체하는 단어
 */
public enum StationStatus {

    ARRIVED("도착"), DEPARTED("출발"), TBD("도착정보없음");

    private final String message;

    StationStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
