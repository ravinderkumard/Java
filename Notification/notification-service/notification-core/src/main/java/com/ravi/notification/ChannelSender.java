
package com.ravi.notification;

public interface ChannelSender {
    SendResult send(Message message);

    record SendResult(boolean success, String externalId, String error) {}
}
