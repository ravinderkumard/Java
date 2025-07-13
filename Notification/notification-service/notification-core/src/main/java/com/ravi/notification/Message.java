package com.ravi.notification;

import java.util.Map;
import java.util.UUID;

public record Message(
	UUID id,
	ChannelType channel,
	String to,
	String body,
	Map<String,String> headers) {}
