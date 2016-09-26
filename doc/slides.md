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

### What is a Servlet

> A servlet is a Java technology-based Web component, managed by a container,
that generates dynamic content.

--

### Servlet Container

#### Servlet containers execute and manage servlets.

Famous containers:
- Tomcat
- Jetty

--

### Servlet Interface

```java

package javax.servlet;

public interface Servlet {
    public void init(ServletConfig config) throws ServletException;

    public ServletConfig getServletConfig();

    public void service(ServletRequest req, ServletResponse res)
	throws ServletException, IOException;

    public String getServletInfo();

    public void destroy();
}

```

--

### HttpServlet


--

### Simple Servlet Demo

(demo)

--

### Request

--

### Response

--


### Request and Response Demo

(demo)

--

### Servlet Lifecycle

- **Loading and Instantiation** \- locate the class and construct a instance of that
  class
- **Initialization** \- `init` with `ServletConfig` configured in DD, before it can handle any request
- **Request Handling** \- handle each request (in a seperate Thread) and modify response
- **End of Service** \- waiting for all the request handled and cleanup resources
  (`destory`)

--

## Filter

--

## Filter (demo)
(demo)

--

### ServletContext

> The ServletContext interface defines a servlet’s view of the Web application
within which the servlet is running.

each web applicaton only has **ONE** `ServletContext`

--

## RequestDispatcher

--


### Event Listeners
- **ServletContextListener**
- ServletContextAttributeListener
- ServletRequestListener
- ServletRequestListener

--

### ServletContext and Listener Demo
(demo)



