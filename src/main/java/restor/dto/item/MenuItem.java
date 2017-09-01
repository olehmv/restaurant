package restor.dto.item;

public class MenuItem extends Item {
	@Override
	public double price() {
		return getPrice();
	}

}
