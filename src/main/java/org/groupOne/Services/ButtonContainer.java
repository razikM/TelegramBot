package org.groupOne.Services;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import org.groupOne.Services.settings_buttons.InfoButton;
import org.groupOne.Services.settings_buttons.StartButton;

import static org.groupOne.Services.button_enam.ButtonName.*;

public class ButtonContainer {
    private final ImmutableMap<String,Button> buttonMap;
    private Map<Long,Settings> userSettings;

    public ButtonContainer(SendMessageBot sendMessageBot) {
       this.userSettings = new HashMap<>();
       this.buttonMap = ImmutableMap.<String, Button>builder()
                .put(START.getButtonName(), new StartButton(sendMessageBot))
                .put(INFO.getButtonName(), new InfoButton(sendMessageBot))
                .build();
    }
    public Button retrieveButton(String buttonIdentifier){
        return  buttonMap.get(buttonIdentifier);
    }
    public Settings getSettingsCurrentUser(Long chatId){
        if( userSettings.containsKey(chatId)){
            return userSettings.get(chatId);
        }
        userSettings.put(chatId,new Settings(chatId));
        return userSettings.get(chatId);
    }
    public Map<Long,Settings> getAlUserSettings(){
        return userSettings;
    }
}
