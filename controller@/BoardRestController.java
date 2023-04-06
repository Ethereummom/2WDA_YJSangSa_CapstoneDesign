package com.planner.godsaeng.api;

import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/Board")
public class BoardRestController {
	final BoardDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 프로퍼티 파일로부터 저장 경로 참조
	@Value("${이미지 경로 설정}")
	String fdir;
	
	@Autowired
	public BoardRestController(BoardDAO dao) {
		this.dao = dao;
	}
	
	@PostMapping("/add")
	public String addBoard(@ModelAttribute BoardDTO board, Model m, 
			@RequestParam("file") MultipartFile file) {
		try {
			// 저장 파일 객체 생성
			File dest = new File(fdir + "/" + file.getOriginalFilename());
			// 파일 저장
			file.transferTo(dest);
			
			dao.insertBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("계시판 추가 과정에서 문제 발생");
			m.addAttribute("error", "계시판 추가 중 오류가 발생했습니다.");
		}
		return "경로 설정";
	}
	
	// Bid: Board id
	@GetMapping("/delete/{Bid}")
	public String deleteBoard(@PathVariable String Bid, Model m) {
		try {
			dao.deleteBoard(Bid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("해당 계시판 삭제 과정에서 문제 발생");
			m.addAttribute("error", "해당 계시판이 정상적으로 삭제되지 않았습니다.");
		}
		return "경로 설정";
	}
	
	@GetMapping("/update/{Bid}")
	public String edit(@PathVariable("Bid") String Bid, Model m) {
		try {
			BoardDTO board = dao.getBoardById(id);
			model.addAttribute("board", board);
			return "board/edit";
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("계시판을 업데이트하는 동안 문제가 발생했습니다");
			m.addAttribute("error", "계시판을 업데이트하지 못했습니다.");
			return "경로 설정";
		}
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") String Bid, @ModelAttribute BoardDTO board, Model m,
			@RequestParam("file") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				// create save file object
				File dest = new File(fdir + "/" + file.getOriginalFilename());
				// save file
				file.transferTo(dest);
			}

			dao.updateBoard(id, board);
			return "redirect:/Board/" + id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("계시판을 업데이트하는 동안 문제가 발생했습니다.);
			m.addAttribute("error", "계시판을 업데이트하지 못했습니다.");
			return "경로 설정";
		}
	}
	
	@GetMapping("/list")
	public String listBoard(Model m) {
		List<BoardDTO> list;
		try {
			list = dao.getBoardList();
			m.addAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("계시판 목록 생성 과정에서 문제 발생");
			m.addAttribute("error", "계시판 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "경로 설정";
	}

	@GetMapping("/{Bid}")
	public String getBoardById(@PathVariable String Bid, Model m) {
		try {
			BoardDTO board = dao.getBoardById(Bid);
			m.addAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("계시판 가져오는 과정에서 문제 발생");
			m.addAttribute("error", "계시판을 정상적으로 가져오지 못했습니다.");
		}
		return "경로 설정";
	}
}
