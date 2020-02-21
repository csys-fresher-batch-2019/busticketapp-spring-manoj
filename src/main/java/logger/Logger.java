package logger;

public class Logger {
	public static Logger getInstance() {
		Logger out = new Logger();
		return out;

	}

	public void getInput(Object message) {
		System.out.println(message);
	}

	public void debug(Object message) {
		System.out.println(message);
	}

	public void info(Object message) {
		System.out.println(message);
	}

	public void error(Object message) {
		System.out.println(message);
	}

}
