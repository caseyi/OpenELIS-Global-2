package org.openelisglobal.typeofsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.typeofsample.dao.TypeOfSampleTestDAO;
import org.openelisglobal.typeofsample.valueholder.TypeOfSampleTest;

@Service
public class TypeOfSampleTestServiceImpl extends BaseObjectServiceImpl<TypeOfSampleTest, String>
		implements TypeOfSampleTestService {
	@Autowired
	protected TypeOfSampleTestDAO baseObjectDAO;

	public TypeOfSampleTestServiceImpl() {
		super(TypeOfSampleTest.class);
	}

	@Override
	protected TypeOfSampleTestDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public TypeOfSampleTest getTypeOfSampleTestForTest(String testId) {
		return getMatch("testId", testId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String id) {
		return baseObjectDAO.getAllMatching("testId", id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String id) {
		return baseObjectDAO.getAllMatching("typeOfSampleId", id);
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(TypeOfSampleTest typeOfSampleTest) {
		getBaseObjectDAO().getData(typeOfSampleTest);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTypeOfSampleTests(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTypeOfSampleTests(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTypeOfSampleTestRecord(String id) {
		return getBaseObjectDAO().getNextTypeOfSampleTestRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTypeOfSampleTests() {
		return getBaseObjectDAO().getAllTypeOfSampleTests();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTypeOfSampleTestCount() {
		return getBaseObjectDAO().getTotalTypeOfSampleTestCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTypeOfSampleRecord(String id) {
		return getBaseObjectDAO().getPreviousTypeOfSampleRecord(id);
	}
}