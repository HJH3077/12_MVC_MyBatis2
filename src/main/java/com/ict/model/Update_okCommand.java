package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Update_okCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getServletContext().getRealPath("/upload");
			MultipartRequest mr = new MultipartRequest(request, path, 100 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			// idx는 파라미터에 존재하지 않으므로 session에서 호출
			VO v = (VO)request.getSession().getAttribute("vo");
			VO vo = new VO();
			vo.setIdx(v.getIdx());
			vo.setName(mr.getParameter("name"));
			vo.setTitle(mr.getParameter("title"));
			vo.setContent(mr.getParameter("content"));
			vo.setEmail(mr.getParameter("email"));
			vo.setPw(mr.getParameter("pw"));
			String f_name2 = mr.getParameter("f_name2");

			// 첨부파일이 없으면 이전파일로 대체한다.
			if (mr.getFile("f_name") == null) {
				vo.setF_name(f_name2);
			} else {
				// 첨부파일이 있으면 첨부파일을 사용한다.
				vo.setF_name(mr.getFilesystemName("f_name"));
			}

			int result = DAO.getUpdate(vo);
			if (result > 0) {
				return "MyController?cmd=onelist&idx=" + vo.getIdx();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
