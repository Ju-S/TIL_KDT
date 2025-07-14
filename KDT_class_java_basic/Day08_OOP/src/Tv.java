public class Tv {  // 참조 자료형

    private int channel;
    private int volume;

    private String brand;
    private boolean power;

    public Tv(String brand){
        this.brand = brand;
        channel = 1;
        volume = 1;
        power = false;
    }

    public void powerOn(){
        power = true;
    }

    public void powerOff(){
        power = false;
    }

    public void channelUp() {
        channel++;
    }

    public void channelDown() {
        channel--;
    }

    public String getBrand() {
        return brand;
    }

    public int getChannel() {
        return channel;
    }

    public boolean getPower() {
        return power;
    }
}
