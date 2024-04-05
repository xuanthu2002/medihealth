package com.mad.medihealth.service;

import com.mad.medihealth.model.DrugUser;

public interface DrugUserService {
	Iterable<DrugUser> getAllByUser(String uid);
}
