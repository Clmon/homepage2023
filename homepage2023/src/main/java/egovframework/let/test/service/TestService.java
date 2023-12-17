package egovframework.let.test.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface TestService {
	

	//TEST 가져오기
	public TestVO selectTest(TestVO vo) throws Exception;
	
	//TEST 등록
	public String insertTest(TestVO vo) throws Exception;
	
	//TEST 목록 가져오기
	public List<EgovMap> selectTestList(TestVO vo) throws Exception;
	
	//TEST 목록 수
	public int selectTestListCnt(TestVO vo) throws Exception;
	
	//TEST 수정하기
	public void updateTest(TestVO vo) throws Exception;
	
	//TEST 삭제하기
	public void deleteTest(TestVO vo) throws Exception;
	
	

}
