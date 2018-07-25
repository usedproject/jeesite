package com.zhidisoft.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/business/market")
public class MarketController {
	
	@RequestMapping("/toMarket")
	public String toMarket() {
		return "marketInfo/listEmailRecord";
	}
}
