# Buffer

This is the implementation of a buffer where users can write to one end and read from another

I used https://github.com/stleary/JSON-java to use JSON objects within Java

There are two clients, one for pushing JSON objects to the stack (pushJSON()) and one for pulling JSON objects (pullJSON())

The code simulates multiple patrons who write custom generated JSON objects to the buffer. For simplicity the code reuses the same
patrons to read and write to/from the buffer.

The ThreadSafeBuffer handles two scenarios.
The first is when a patron wants to read from the buffer when it is empty.
The second is when a patron wants to write to the buffer when it exceeds the buffer size.

In both scenario's the thread waits until the scenario is no longer true (when notifyAll() is used)
Assuming the buffer size is always atleast one. The two threads shouldn't be in a deadlock at any point because the buffer cannot be
greater than the minimum size (1) and empty at the same time.

Currently the console prints a lot of information for demonstration purposes. 

The classes use synchronized methods so a patron will either be writing or reading at any time, stopping any clashes.
