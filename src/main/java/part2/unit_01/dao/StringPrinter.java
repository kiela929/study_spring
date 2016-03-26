package part2.unit_01.dao;

public class StringPrinter implements Printer {
	private StringBuffer buffer =new StringBuffer();

	@Override
	public void print(String message) {
		this.buffer.append(message);
	}
	
	public String toString(){
		return this.buffer.toString();
	}

}
