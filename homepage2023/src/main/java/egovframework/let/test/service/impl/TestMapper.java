package egovframework.let.test.service.impl;


import java.util.List;

import egovframework.let.test.service.TestVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("testMapper")
public interface TestMapper {
	
	//TEST 가져오기
	TestVO selectTest(TestVO vo) throws Exception;
	
	//TEST 등록
	void insertTest(TestVO vo) throws Exception;
	
	//TEST 목록 가져오기
	List<EgovMap> selectTestList(TestVO vo) throws Exception;
	
	//TEST 목록 수
	int selectTestListCnt(TestVO vo) throws Exception;
	
	//TEST 수정하기
	void updateTest(TestVO vo) throws Exception;
	
	//TEST 삭제하기
	void deleteTest(TestVO vo) throws Exception;

}
