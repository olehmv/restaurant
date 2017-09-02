package restor.dao.client;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.RestorDAO;
import restor.dto.client.Client;
@Component
public class ClientJdbcDAO extends RestorDAO<Client> implements IClientDAO {
	private String insert = "insert into client (name) values(?)";
	private String update = "update client set name=? where id=?";
	private String delete = "delete from client where id=?";
	private String fetchClient = "select * from client where id=?";
	private String fetchClients = "select * from client";
	private List<Client> clients;
	private Client client;


	@Override
	public Client insert(Client dto) {

		try {
			ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getName());

			ResultSet rs = ps.getGeneratedKeys();

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Inserted");
			} else {
				System.out.println("not Inserted");
			}
			rs = ps.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			dto.setId(auto_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Client update(Client dto) {

		try {
			ps = con.prepareStatement(update);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getId());

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Updated");
			} else {
				System.out.println("not Updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Client delete(Client dto) {
		try {
			ps = con.prepareStatement(delete);
			ps.setInt(1, dto.getId());
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("deleted");
			} else {
				System.out.println("not deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Client fetchClient(int dto_id) {
		try {
			ps = con.prepareStatement(fetchClient);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				client = new Client();
				client.setId(id);
				client.setName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public List<Client> fetchClients() {
		try {
			ps = con.prepareStatement(fetchClients);
			ResultSet rs = ps.executeQuery();
			clients = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				client = new Client();
				client.setId(id);
				client.setName(name);
				clients.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clients;
	}

}
