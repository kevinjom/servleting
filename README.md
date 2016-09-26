# Servleting
A simple example project shows how to use Java Servlet and how other frameworks
(like Spring) integrate with it.


## Build
`./gradlew build`

## Run
Change to the `servletxx` directory and run `../gradlew tomcatRun`, then you can test with `curl -i -b userId=bla localhost:8088/inbox`

## Servlet 2.x
You can find examples that demenstrate servlet 2.5 usage under directory `servlet2x`. Features covered by these examples include:

- Servlet Inteface
- Deployment Descriptor, aka DD (web.xml)
- Request
- Response
- Filter
- Event Listeners

## Servlet 3.0
Check the `servlet3x` directory out, you should find that these examples show usage of:

- Annotations for declaring servlet components (Servlet, Filter, Listener,etc)
- Dynamic servlet components registration

