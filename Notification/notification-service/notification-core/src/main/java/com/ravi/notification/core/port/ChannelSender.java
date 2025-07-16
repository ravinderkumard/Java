
package com.ravi.notification.core.port;

import com.ravi.notification.core.model.Message;
import com.ravi.notification.core.model.SendResult;

public interface ChannelSender {
    SendResult send(Message message);

    //record SendResult(boolean success, String externalId, String error) {}
}
