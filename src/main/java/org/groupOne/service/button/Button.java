package org.groupOne.service.button;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Button {
    void execute(Update update, Settings settings);
}
