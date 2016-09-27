title: Introduction to java Servlet
author:
  name: kevinjom
  twitter: _kevinjom
  url: http://kevinjom.github.io
output: servleting.html
theme: jdan/cleaver-retro
controls: false

--

# Introduction to Java Servlet

--

### What we are doing
# WEB

--

### Web server & container

![server](server.jpg)

--

### What is a Servlet

#### A servlet is a Java technology-based Web component, managed by a container, that generates dynamic content.

(CONFUSED)

--

### What is a Servlet

- Servlet is a technology that is used to create web application 
- Web component that is deployed on server side to create dynamic web pages
- Servlet is an API that provides many Interfaces and classes including documentation
- Servlet is an Interface that must be implemented for creating any servlet
- Servlet is a class that extend the functionalities of server  and responds to the incoming requests


--

### Servlet Container

#### Servlet containers execute and manage servlets.

> The servlet container is a part of a Web server or application server that
 provides the network services over which requests and responses are sent,
 decodes MIME-based requests, and formats MIME-based responses. A servlet
 container also contains and manages servlets through their lifecycle.

> A servlet container can be built into a host Web server, or installed as an
 add-on component to a Web Server via that server’s native extension API. Servlet
 containers can also be built into or possibly installed into Web-enabled
 application server

--

### Servlet Interface

```java

package javax.servlet;

public interface Servlet {
    public void init(ServletConfig config) throws ServletException;

    public ServletConfig getServletConfig();

    public void **service**(ServletRequest req, ServletResponse res)
	throws ServletException, IOException;

    public String getServletInfo();

    public void destroy();
}

```

--

### HttpServlet

```java
protected void service(HttpServletRequest req, HttpServletResponse resp)

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
...
```


--

### Simple Servlet Demo

```java
public class InboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("hello sevleting");
    }
}
```

--

### How a java web app looks like
![directory-structure](dir.jpg)

--

### ServletRequest

The request object encapsulates all information from the client request.A new
request object will be created each time a client request received and this
object is only available in servlet.

You can get these information from request object
- Request URL
- Parameters
- Headers
- Cookies
- Attributes
- Reqeust Body via InputStream
- ...

--

### ServletResponse

The response object encapsulates all information to be returned from the server
to the client.

```java
    public void setStatus(int sc);
    public void addHeader(String name, String value);
    public PrintWriter getWriter() throws IOException;
    ...
```

--

### Servlet Lifecycle

- **Loading and Instantiation** \- locate the class and construct a instance of that
  class
- **Initialization** \- `init` with `ServletConfig` configured in DD, before it can handle any request
- **Request Handling** \- handle each request (in a seperate Thread) and modify response
- **End of Service** \- waiting for all the request handled and cleanup resources
  (`destory`)

--

### Filter, what is it

A filter is a reusable piece of code that can transform the content of HTTP requests, responses, and header information. Filters do not generally create a response or respond to a request as servlets do, rather they modify or adapt the requests for a resource, and modify or adapt responses from a resource.

### Filter interface

```java
public interface Filter {
 public void init(FilterConfig filterConfig) throws ServletException;
 public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException;
 public void destroy();
}
```

### Filter, what can it do

- Authentication filters
- Logging and auditing filters
- Image conversion filters
- Data compression filters
- Encryption filters
- Tokenizing filters
- Filters that trigger resource access events
- XSL/T filters that transform XML content
- MIME-type chain filters
- Caching filters


--

### ServletContext

> The ServletContext interface defines a servlet’s view of the Web application
within which the servlet is running.

each web applicaton only has **ONE** `ServletContext`

--

### Event Listeners
- **ServletContextListener**
- ServletContextAttributeListener
- ServletRequestListener
- ServletRequestListener

--

# Servlet 3.0

--

### Whats new

- **Ease of Development with annotations**
- **Dynamic Registration of Servlets and Filters**
- Asynchronous support
- Pluggability
- Resources in bundled jar files

--

### Drop web.xml, use Annotations

- `@WebServlet` – Define a Servlet
- `@WebFilter` – Define a Filter
- `@WebListener` – Define a Listener
- `@WebInitParam` – Define init params
- ...

--

### Dynamic Servlet/Filter Registration

#### new methods had been added to `ServletContext` to make it possible to register servlet/filter/listener with java code.

```java
public ServletRegistration.Dynamic addServlet(...)

public FilterRegistration.Dynamic addFilter(...)

public void addListener()
```

--

### Demo codebase

check it out on github https://github.com/kevinjom/servleting
