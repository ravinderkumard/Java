package com.ravi.notification.core.model;

import java.util.Map;
import java.util.Objects;

/**
 * Immutable request object representing a caller’s intent to send a notification.
 *
 * <p>Recommended usage:
 * <pre>{@code
 * SendRequest req = SendRequest.of(
 *         "WELCOME_EMAIL",
 *         "user‑123",
 *         ChannelType.EMAIL,
 *         Map.of("firstName", "Ravi")
 * );
 * }</pre>
 *
 * <p>All fields are non‑null; {@link #validate()} throws {@link IllegalArgumentException}
 * if any required property is missing or blank.
 */
public record SendRequest(
        String templateCode,
        String userId,
        ChannelType preferredChannel,
        Map<String, String> variables
) {

    /** Factory method with defensive copies and validation. */
    public static SendRequest of(
            String templateCode,
            String userId,
            ChannelType preferredChannel,
            Map<String, String> variables
    ) {
        SendRequest request = new SendRequest(
                templateCode,
                userId,
                preferredChannel,
                variables == null ? Map.of() : Map.copyOf(variables)
        );
        request.validate();
        return request;
    }

    /** Basic invariants to fail fast before hitting the service layer. */
    public void validate() {
        if (templateCode == null || templateCode.isBlank()) {
            throw new IllegalArgumentException("templateCode must not be blank");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId must not be blank");
        }
        Objects.requireNonNull(preferredChannel, "preferredChannel must not be null");
    }
}
