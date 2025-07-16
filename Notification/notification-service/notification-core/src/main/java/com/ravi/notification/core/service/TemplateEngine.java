package com.ravi.notification.core.service;

import com.ravi.notification.core.model.Message;
import com.ravi.notification.core.model.SendRequest;

public interface TemplateEngine {
	Message render(SendRequest request);
}
