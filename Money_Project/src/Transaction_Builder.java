
public class Transaction_Builder {
	private Transaction t;

	public Transaction_Builder(Transaction t) {
		this.t = t;
	}

	public Transaction_Builder name(String name) {
		t.setName(name);
		return this;
	}

	public Transaction_Builder type(String type) {
		t.setType(type);
		return this;
	}

	public Transaction_Builder amount(double amount) {
		t.setAmount(amount);
		return this;
	}

	public Transaction_Builder note(String note) {
		t.setNote(note);
		return this;
	}

	public Transaction transaction() {
		return this.t;
	}
}
