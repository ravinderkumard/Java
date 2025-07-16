package com.ravi.notification.core.service;

import java.util.Map;
import java.util.UUID;

import com.ravi.notification.core.model.ChannelType;
import com.ravi.notification.core.model.Message;
import com.ravi.notification.core.model.SendRequest;
import com.ravi.notification.core.model.SendResult;
import com.ravi.notification.core.port.ChannelSender;

public class NotificationService {
	private TemplateEngine templateEngine;
	private final Map<ChannelType,ChannelSender> senders;
	
	public NotificationService(TemplateEngine templateEngine,Map<ChannelType,ChannelSender> senders) {
		this.templateEngine = templateEngine;
		this.senders = senders;
	}
	
	public UUID send(SendRequest request) {
        Message message = templateEngine.render(request);
        ChannelSender sender = senders.get(request.preferredChannel());
        if (sender == null) {
            throw new IllegalStateException("No sender found for " + request.preferredChannel());
        }
        SendResult result = sender.send(message);
        return result.id();
    }
}
