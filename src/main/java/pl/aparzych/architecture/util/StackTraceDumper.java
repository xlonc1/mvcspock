package pl.aparzych.architecture.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceDumper {
  private StackTraceDumper() {
  }

  static String dumpStackTrace(Throwable exception) {
    StringWriter stringWriter = new StringWriter();
    exception.printStackTrace(new PrintWriter(stringWriter));

    return stringWriter.toString();
  }
}
