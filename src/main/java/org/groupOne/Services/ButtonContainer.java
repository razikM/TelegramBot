package org.groupOne.Services;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static org.groupOne.Services.ButtonName.START;

public class ButtonContainer {
    private final ImmutableMap<String,Button> buttonMap;
    private Map<Long,Settings> userSettings;

    public ButtonContainer(SendMessageBot sendMessageBot) {
       this.userSettings = new HashMap<>();
       this.buttonMap = ImmutableMap.<String, Button>builder()
                .put(START.getButtonName(), new StartButton(sendMessageBot))
                .build();
    }
    public Button retrieveButton(String buttonIdentifier){
        return  buttonMap.get(buttonIdentifier);
    }
    public Settings getSettingsCurrentUser(Long chatId){
        if( userSettings.containsKey(chatId)){
            return userSettings.get(chatId);
        }
        userSettings.put(chatId,new Settings());
        return userSettings.get(chatId);
    }
}