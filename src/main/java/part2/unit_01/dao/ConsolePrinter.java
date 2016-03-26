package part2.unit_01.dao;

public class ConsolePrinter implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);

	}

}
