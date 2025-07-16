package com.ravi.notification.core.model;

import java.util.UUID;

public record SendResult(UUID id,boolean success,String details) {

}
