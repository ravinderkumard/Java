package com.ravi.notification.core.model;

import java.util.Map;
import java.util.UUID;

public record Message(
	UUID id,
	ChannelType channel,
	String to,
	String body,
	Map<String,String> headers) {}
