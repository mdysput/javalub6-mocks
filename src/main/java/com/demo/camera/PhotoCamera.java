package com.demo.camera;

public class PhotoCamera implements WriteListener {

    private boolean isOn = false;
    private final ImageSensor sensor;
    private final Card card;

    public PhotoCamera(ImageSensor sensor, Card card) {
        this.sensor= sensor;
        this.card = card;
    }


    public boolean isOn(){
        return isOn;
    }

    private void turnOn() {
        isOn = true;
        sensor.turnOn();
    }

    private void turnOff() {
        isOn = false;
        sensor.turnOff();
    }

    public void pressButton() {
        if(isOn){
            //sensor.read();
            card.write(sensor.read());
        }
    }

    public void powerButton(){
        if(isOn){
            turnOff();
        }
        else{
            turnOn();
        }
    }

    @Override
    public void writeCompleted() {

    }
}

