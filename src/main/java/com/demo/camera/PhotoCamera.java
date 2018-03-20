package com.demo.camera;

public class PhotoCamera implements WriteListener {

    private boolean isOn = false;
    private final ImageSensor sensor;
    private final Card card;
    private boolean savingInProgress = false;
    private boolean powerButtonPressed = false;

    PhotoCamera(ImageSensor sensor, Card card) {
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
            byte[] image = sensor.read();
            savingInProgress = true;
            card.write(image);
        }
    }
    public void powerButton(){
        if(isOn && !savingInProgress){
            turnOff();
        }
        else if(!isOn){
            turnOn();
            powerButtonPressed = false;
        }
        else{
            powerButtonPressed = true;
        }
    }
    @Override
    public void writeCompleted() {
        savingInProgress = false;
        if(powerButtonPressed){
            powerButton();
        }
    }
}

