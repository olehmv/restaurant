package restor.dto.admin;

import java.util.List;

import restor.dto.item.Item;
import restor.dto.order.Order;

public interface IProcess {

	Order process(String description, List<Item> items);

}
