package com.example.appengine.java8;

import com.github.jknack.handlebars.Template;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jknack.handlebars.Handlebars;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "HelloAppEngineHbs", value = "/hello-hbs")
public class HelloAppEngineHbs extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/plain");
    try {
      response.getWriter().print(HelloAppEngineHbs.createWithHandlebar()); // This fails
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

    /**
     * This method is used to initialize Handlebars, register a helper and render a template. Calling this method
     * in a local dev server context fails. Calling it in a Tescase succeed.
     *
     * @return
     * @throws Exception
     */
  public static String createWithHandlebar() throws Exception {
      Handlebars handlebars = new Handlebars();
      File helperPath = new File(HelloAppEngineHbs.class.getResource("/helpertest.js").getPath());
      handlebars.registerHelpers(helperPath);  // <-- this is where it fails!
      Template template = handlebars.compileInline("Hello {{this}}!");
      return template.apply("Handlebars.java");
  }
}