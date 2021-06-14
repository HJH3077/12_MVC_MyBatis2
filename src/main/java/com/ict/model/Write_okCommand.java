package com.ict.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Write_okCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getServletContext().getRealPath("/upload");

			MultipartRequest mr = new MultipartRequest(request, path, 100 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			VO vo = new VO();
			vo.setName(mr.getParameter("name"));
			vo.setTitle(mr.getParameter("title"));
			vo.setContent(mr.getParameter("content"));
			vo.setEmail(mr.getParameter("email"));
			vo.setPw(mr.getParameter("pw"));

			// 첨부파일이 있을 때와 첨부파일이 없을 때를 구분하자 (이걸 안해주면 나중에 오류남)
			if (mr.getFile("f_name") != null) {
				vo.setF_name(mr.getFilesystemName("f_name")); // 파일 있을 때
				// filesystemname = 저장 이름
			} else {
				vo.setF_name(""); // 파일 없을 떄, 이거 안하면 DB처리 시 오류남
			}

			int result = DAO.getInsert(vo);
			if (result > 0) {
				return "MyController?cmd=list";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
