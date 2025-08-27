1. What is an event in Event-Driven Architecture? (with real-world example)

In Event-Driven Architecture (EDA), an event is a notification that something significant has happened in the system — a change in state.
Real-world example: Imagine you're waiting for a pizza delivery. When the pizza arrives, the delivery person rings the doorbell. The doorbell ringing is an event — it informs you that something important has occurred (pizza has arrived), and you can now respond (go get the pizza).

2. Compare EDA with Request–Response Architecture

| **Feature**                 | **Event-Driven Architecture (EDA)**                    | **Request–Response Architecture**             |
|----------------------------|--------------------------------------------------------|-----------------------------------------------|
| **Communication Style**     | Asynchronous                                           | Synchronous                                   |
| **Coupling Between Services** | Loosely Coupled                                        | Tightly Coupled                               |
| **Scalability**              | Highly Scalable                                        | Limited by synchronous calls                  |
| **Resilience**               | More resilient to failures (via retries, queues)       | Failures propagate to caller                  |
| **Complexity**               | More complex infrastructure                            | Simpler to implement and understand           |
| **Real-Time Processing**     | Supports real-time or near real-time reactions         | Not optimized for real-time scenarios         |

Advantages of EDA:

   - Better for scaling and fault isolation

   - Enables real-time data flow and decoupled services

Advantages of Request–Response:

   - Easier to develop for simple systems

   - Useful when immediate feedback is required (e.g., form submission)

3. While developing an e-commerce application, how would you use Event-Driven Architecture (EDA) to manage the following scenarios?
    - Placing an order
    - Sending a confirmation email
    - Updating inventory

    a. Placing an Order

    -  When a customer places an order, an OrderPlaced event is published.
    - Other services subscribe to this event and act accordingly.

    b. Sending a Confirmation Email

    - An Email Service listens for the OrderPlaced event and sends a confirmation email to the customer.

    c. Updating Inventory

    - An Inventory Service consumes the OrderPlaced event and reduces the stock for the purchased item.
    - Each of these actions is handled independently, allowing services to work in parallel and scale separately.

4. Why EDA is a Good Fit for Microservices and Cloud-Native Systems

    - Loose Coupling: Services communicate via events and don’t depend on each other directly, allowing independent development, deployment, and scaling.

    - Scalability and Flexibility: EDA naturally supports horizontal scaling. Services can scale based on event volume (e.g., more email processors during sales).

    - Resilience: Events can be retried or delayed without crashing the system, making it fault-tolerant.

5. How EDA Helps Build Scalable Systems (with real-world use cases)

    Benefits for Scalability:

    - Services can scale independently based on demand.
    - Events can be stored, replayed, or queued during spikes.
    - No need to block while waiting for responses.

    🔄 Use Case 1: Ride-Sharing Apps (e.g., Uber, Lyft)

    Events like RideRequested, DriverAssigned, and RideCompleted are published and handled by different microservices.
    This allows for real-time updates, dynamic pricing, and location tracking — all in parallel.

    🛒 Use Case 2: E-Commerce Order Fulfillment

    OrderPlaced triggers inventory updates, payment processing, and email notifications as separate workflows.
    Each component scales independently during high traffic (e.g., Black Friday).


DAY-2

1. What is the Publish-Subscribe Model in Kafka?

The publish-subscribe model (or pub-sub) in Kafka is a messaging pattern where:

    - Producers (publishers) send messages to Kafka topics.

    - Consumers (subscribers) read messages from those topics.

In Kafka:
Topics are like channels or subjects where data is published.
Multiple consumers can subscribe to the same topic, and they can be part of different consumer groups depending on how you want message distribution.
Messages are not deleted after consumption (until retention limit) — so other consumers can still read them.
You can have many independent consumers, each reading the same data at their own pace.

2. How Does Kafka Ensure Message Durability?

Kafka ensures durability in several ways:

    1. Disk Persistence
    - Messages are written to disk before acknowledging the producer (write-ahead log).
    - Kafka persists messages in segments, not in memory.

    2. Replication
    - Each partition can have replicas across multiple brokers (controlled by replication factor).
    - If one broker crashes, another broker (replica) takes over.

    3. Acknowledgment Settings
    - Producers can specify acks=all, meaning the message is only considered "written" once all in-sync replicas acknowledge it.

    4. Configurable Retention
    - Messages are retained for a set period (retention.ms) or until the log reaches a certain size (retention.bytes).

3. What is the Role of a Kafka Topic and Partition?

Topic- A topic is a category or feed name to which messages are published.
Think of it like a table or folder for messages.

Partition- A topic is split into partitions for scalability and parallelism.
Each partition is:
- Ordered (messages in a partition have a strict order).
- Individually persisted and replicated.
- Read independently by consumers in a group.

Why It Matters:
More partitions = better throughput, scalability, and parallelism.
Partitioning allows multiple consumers to read in parallel.

4. What Happens if a Consumer Fails While Processing a Message?
Scenario:
A consumer reads a message but crashes before fully processing it or committing the offset.

Kafka Behavior:
- Kafka tracks consumer offsets (i.e., how far the consumer has read).
- If the consumer crashes before committing the offset, it will re-read the message on restart (possible duplicate).
- If the offset is committed after processing, the message is considered consumed.

Solutions:

- At-least-once delivery is default (message may be reprocessed).
- For exactly-once, you need Kafka Streams or transactional producers/consumers.
- Use manual offset control for fine-grained handling.

5. Compare Kafka with another messaging system like RabbitMQ or MQTT.


| **Feature**       | **Kafka**                                  | **RabbitMQ**                             |**MQTT**                                   |
|-------------------|---------------------------------------------|-------------------------------------------|---------------------------------------------|
| **Type**          | Distributed log / stream processing         | Message broker (AMQP)                     | Lightweight publish-subscribe (IoT)         |
| **Durability**    | Very high (disk + replication)              | Good (persistent queues)                  | Low (in-memory or persistent options)        |
| **Ordering**      | Per partition                               | Per queue                                 | Not guaranteed                               |
| **Throughput**    | Extremely high (millions/sec)               | Lower than Kafka                          | Optimized for low-bandwidth networks         |
| **Latency**       | Low                                         | Low–medium                                | Ultra low                                    |
| **Use Case Fit**  | Big data, logs, real-time analytics         | Task queues, transactional workflows      | IoT, mobile, sensor networks                 |
| **Protocol**      | TCP-based (custom Kafka protocol)           | AMQP                                      | MQTT (lightweight TCP/IP)                   |
| **Consumer Model**| Pull-based                                  | Push-based                                | Push-based                                   |
