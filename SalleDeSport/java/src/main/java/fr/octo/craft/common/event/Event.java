package fr.octo.craft.common.event;

import java.time.ZonedDateTime;

public abstract class Event {

    public String messageName; // identify the message that needs to be acted upon
    public String serviceWhichOwnsTheContractOfTheMessage; // identify the message that needs to be acted upon
    public String messageVersion; // allows for parsing of different versions, in case of breaking changes
    public MessageId messageId; // allow to build a causal chain of each command in a system
    public CorrelationId correlationId; // allows for checking for errors in distributed processes
    public CausationId causationId; // allow to build a causal chain of each command in a system
    public ZonedDateTime utcTimestamp; // for context (along with data)
    public String authenticationToken; // for security and auth
    public String entityVersion; // For commands this is taken from query (similarly to an ETag), and used in all messages for idempotency
    public String resourceWhichRaisedTheEvent; // [Event-only] for context (along with data)
}
