
package ai.rnt.main.util;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The java class ExceptionReaderUtil
 * 
 * @author Swapnil Mane
 * @version 1.0
 */
public class ExceptionReaderUtil {
	/** default constructor */
	ExceptionReaderUtil() {
	}

	/**
	 * This method is used to read exception.
	 * 
	 * @param e
	 * @return String
	 */
	public static String readException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();

	}
}

