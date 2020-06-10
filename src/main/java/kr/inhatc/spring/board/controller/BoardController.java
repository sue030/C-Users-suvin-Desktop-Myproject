package kr.inhatc.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.board.dto.BoardDto;
import kr.inhatc.spring.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test() {
		System.out.println("페이지 이동중...");
		return "index";  
	} 
	
	@RequestMapping("/board/boardList")
	public String boardList(Model model) {
		List<BoardDto> list = boardService.boardList();
		System.out.println("========>" + list.size());
		model.addAttribute("list", list);
		return "board/boardList";
	}
	@RequestMapping("/board/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("/board/boardInsert")
	public String boardInsert(BoardDto board) {
		boardService.boardInsert(board);
		return "redirect:/board/boardList";
	}
	@RequestMapping("/board/boardDetail")
	public String boardDetail(@RequestParam int boardIdx, Model model) {
		System.out.println("boardIdx >>>>>>>>>>>>>>>>>>>>>>>>>" + boardIdx);
		BoardDto board = boardService.boardDetail(boardIdx);
		
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	@RequestMapping("/board/boardUpdate")
	public String boardUpdate(BoardDto board) {
		boardService.boardUpdate(board);
		return "redirect:/board/boardList";
	}
	@GetMapping @PostMapping @DeleteMapping @PutMapping
	@RequestMapping("/board/boardDelete")
	public String boardDelete(@RequestParam(value="boardIdx", defaultValue="0") int boardIdx) {
		System.out.println(">>>>>>>>>>>>>>>>>>delete 실행");
		boardService.boardDelete(boardIdx);
		return "redirect:/board/boardList";
	}
}
