package restor.dao;

import restor.dto.admin.Admin;

public interface IAdminDAO extends IFetchOrders,IFetchOrder{
	Admin save(Admin dto);

	Admin update(Admin dto);

	
	Admin delete(Admin dto);


}
