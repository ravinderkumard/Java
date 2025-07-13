# Notification Service


# Class Diagram
<img width="614" height="352" alt="image" src="https://github.com/user-attachments/assets/40a38476-3742-4554-9549-1dfaa4c7d608" />

Add Class diagram generation template
@startuml
skinparam style strictuml

interface ChannelSender {
  +send(msg: Message): SendResult
}

class NotificationService {
  -senders: Map<ChannelType, ChannelSender>
  +send(req: SendRequest): UUID
}

class TemplateEngine {
  +render(req: SendRequest): Message
}

enum ChannelType {
  EMAIL
  SMS
  PUSH
}

NotificationService --> ChannelSender : uses
NotificationService --> TemplateEngine
ChannelSender <|-- EmailSender
ChannelSender <|-- SmsSender
@enduml




# Sequence Diagram

<img width="788" height="328" alt="image" src="https://github.com/user-attachments/assets/ce5f52ec-60ff-4876-9874-b4621649dc78" />

Sequence Diagram generation Template

@startuml
skinparam style strictuml
actor Client
Client -> NotificationController: POST /notifications
NotificationController -> NotificationService: send(req)
NotificationService -> TemplateEngine: render(req)
TemplateEngine --> NotificationService: Message
NotificationService -> KafkaProducer: publish(Message)
KafkaProducer --> NotificationService: offset
NotificationService --> NotificationController: UUID

KafkaConsumer -> EmailSender: send(Message)
EmailSender -> SendGridAPI: POST /mail/send
SendGridAPI --> EmailSender: 202 ACCEPTED
EmailSender --> KafkaConsumer: SendResult
@enduml
