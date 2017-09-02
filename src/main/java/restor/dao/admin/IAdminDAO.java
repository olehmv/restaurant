package restor.dao.admin;

import restor.dto.admin.Admin;

public interface IAdminDAO extends IFetchAdmin,IFetchAdmins{
	Admin save(Admin dto);

	Admin update(Admin dto);

	
	Admin delete(Admin dto);


}
