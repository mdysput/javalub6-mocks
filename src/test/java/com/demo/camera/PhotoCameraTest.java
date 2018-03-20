package com.demo.camera;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

public class PhotoCameraTest {

    @Test
    public void cameraIsOffAfterCreating(){
        Card card= mock(Card.class);
        ImageSensor sensor = mock(ImageSensor.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        Assertions.assertThat(camera.isOn()).isFalse();
    }

    @Test
    public void afterPressingPowerButtonCameraWillBeOnIfItWasOff(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        assert !camera.isOn();
        camera.powerButton();
        Assertions.assertThat(camera.isOn()).isTrue();
    }

    @Test
    public void afterPressingPowerButtonCameraWillBeOnIfItWasOffAndSensorWillBeAlsoOn(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        assert !camera.isOn();
        camera.powerButton();
        Mockito.verify(sensor).turnOn();
    }
    @Test
    public void afterPressingPowerButtonCameraWillBeOffIfItWasOnAndSensorWillBeAlsoOff(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        assert camera.isOn();
        camera.powerButton();
        Mockito.verify(sensor).turnOff();
    }

    @Test
    public void pressingTriggeringButtonIfPowerIsOffDoNothing(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.pressButton();
        Mockito.verify(sensor, never()).read();
    }
    @Test
    public void pressingTriggeringButtonIfPowerIsOnCopyDataFromSensor(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        camera.pressButton();
        Mockito.verify(sensor).read();
    }
     @Test
    public void pressingTriggeringButtonIfPowerIsOnCopyDataFromSensorToCard(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        camera.pressButton();
        Mockito.verify(sensor).read();
        Mockito.verify(card).write(Mockito.any());
    }
      @Test
    public void pressingTriggeringButtonIfPowerIsOnCopyDataFromSensorToCardAndSaveInFewSeconds(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        camera.pressButton();
        Mockito.verify(sensor).read();
        Mockito.verify(card).write(Mockito.any());
    }

    @Test
    public void ifSavingIsInProgressPowerCantBeOff(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        camera.pressButton();
        camera.powerButton();
        Assertions.assertThat(camera.isOn()).isTrue();
    }

    @Test
    public void ifPowerButtonisPressedAndSavingIsInProgressThenCameraWillBeOffAfterSavingEverything(){
        ImageSensor sensor = mock(ImageSensor.class);
        Card card= mock(Card.class);
        PhotoCamera camera = new PhotoCamera(sensor, card);
        camera.powerButton();
        camera.pressButton();
        camera.powerButton();
        assert camera.isOn();
        camera.writeCompleted();
        Assertions.assertThat(camera.isOn()).isFalse();
    }









}