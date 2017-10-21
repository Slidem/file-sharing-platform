# JMS Messaging system

- The project uses ActiveMQ as it's main messaging system.

## Notes:

### The file-sharing-jms-common jar:

#### The _TopicsRepository_ class
- Contains a topic repository where all the shared topic names are available.
- Use this repository to get the name of the topic you want to **publish** to **OR** the topic you want to **consume** the messages from.
- If you want you can overrite the names of the topics by providing them as environment properties

 #### The _JmsMessageConverter_
 - The messages are sent on the topics as strings, in JSON format.
 - For this a **JmsMessageConverter** is provided, that can serialize / deserialize topic messages. Use this to have a common mechanism that handles json objects (currently, uses gson library)
 

 ## Examples:
 
 ### Message Producers: _user created event_
 - Located in the file-sharing-auth (core) project, use this an example of usage of spring's *ApplicationEventPublisher*: packages: **com.file.sharing.jms**. See also **FileSharingUserServiceImpl**
 
 
 
 
